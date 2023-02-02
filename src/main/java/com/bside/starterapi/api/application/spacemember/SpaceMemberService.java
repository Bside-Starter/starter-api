package com.bside.starterapi.api.application.spacemember;

import com.bside.starterapi.api.domain.space.Space;
import com.bside.starterapi.api.domain.space.SpaceRepository;
import com.bside.starterapi.api.domain.spacemember.SpaceMember;
import com.bside.starterapi.api.domain.spacemember.SpaceMemberRepository;
import com.bside.starterapi.api.domain.user.User;
import com.bside.starterapi.api.domain.user.UserRepository;
import com.bside.starterapi.api.presentation.spacemember.dto.SpaceExitResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class SpaceMemberService {
    private final SpaceMemberRepository spaceMemberRepository;
    private final UserRepository userRepository;
    private final SpaceRepository spaceRepository;

    @Transactional
    public SpaceExitResponse exit(Long userId, Long spaceId) {
        Space space = spaceRepository.getById(spaceId);
        User attendee = userRepository.getById(userId);
        SpaceMember spaceMember = spaceMemberRepository.findBySpaceAndUser(space, attendee)
                .orElseThrow();
        spaceMember.exit();
        return new SpaceExitResponse(userId, spaceId);
    }
}
