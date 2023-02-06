package com.bside.starterapi.api.application.space;

import com.bside.starterapi.api.domain.space.Space;
import com.bside.starterapi.api.domain.space.SpaceRepository;
import com.bside.starterapi.api.domain.space.SpaceState;
import com.bside.starterapi.api.domain.spacemember.SpaceMember;
import com.bside.starterapi.api.domain.user.User;
import com.bside.starterapi.api.domain.user.UserRepository;
import com.bside.starterapi.api.presentation.space.dto.SpaceJoinRequest;
import com.bside.starterapi.api.presentation.space.dto.SpaceJoinResponse;
import com.bside.starterapi.api.presentation.space.dto.SpaceResponse;
import com.bside.starterapi.support.presentation.StatusDataResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class SpaceService {
    private final SpaceRepository spaceRepository;
    private final UserRepository userRepository;

    @Transactional
    public SpaceJoinResponse join(Long userId, SpaceJoinRequest request) {
        User attendee = userRepository.getById(userId);
        Space space = spaceRepository.findByCode(request.getCode()).orElseThrow();
        SpaceMember spaceMember = SpaceMember.of(space, attendee);
        space.addAttendee(spaceMember);
        return new SpaceJoinResponse(spaceRepository.save(space).getId());
    }

    @Transactional(readOnly = true)
    public StatusDataResult<GetSpacesStatus, List<SpaceResponse>> getMySpaces(Long userId) {
        User user = userRepository.getById(userId);
        List<Space> spaces = spaceRepository.findAllByUserAndState(user, SpaceState.VALID);
        if (spaces.isEmpty()) {
            return new StatusDataResult<>(GetSpacesStatus.NOT_EXIST_SPACE, Collections.emptyList());
        }
        List<SpaceResponse> result = spaces.stream()
                .map(SpaceResponse::from)
                .collect(Collectors.toList());
        return new StatusDataResult<>(GetSpacesStatus.SUCCESS, result);
    }

    @Transactional
    public Long delete(Long userId, Long spaceId) {
        Space space = spaceRepository.getById(spaceId);
        if (!Objects.equals(space.getUser().getId(), userId)) {
            throw new IllegalArgumentException("삭제할 수 있는 권한이 없습니다.");
        }
        space.delete();
        return space.getId();
    }
}
