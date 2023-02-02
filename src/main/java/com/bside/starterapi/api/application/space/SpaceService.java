package com.bside.starterapi.api.application.space;

import com.bside.starterapi.api.domain.space.Space;
import com.bside.starterapi.api.domain.space.SpaceRepository;
import com.bside.starterapi.api.domain.spacemember.SpaceMember;
import com.bside.starterapi.api.domain.user.User;
import com.bside.starterapi.api.domain.user.UserRepository;
import com.bside.starterapi.api.presentation.space.dto.SpaceJoinRequest;
import com.bside.starterapi.api.presentation.space.dto.SpaceJoinResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
