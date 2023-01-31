package com.bside.starterapi.unit.spacemember;

import com.bside.starterapi.api.application.spacemember.SpaceMemberService;
import com.bside.starterapi.api.domain.space.Space;
import com.bside.starterapi.api.domain.space.SpaceRepository;
import com.bside.starterapi.api.domain.spacemember.SpaceMember;
import com.bside.starterapi.api.domain.spacemember.SpaceMemberRepository;
import com.bside.starterapi.api.domain.spacemember.SpaceMemberState;
import com.bside.starterapi.api.domain.user.User;
import com.bside.starterapi.api.domain.user.UserRepository;
import com.bside.starterapi.support.fixtures.SpaceFixtures;
import com.bside.starterapi.support.fixtures.UserFixtures;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class SpaceMemberServiceTest {
    @Mock
    private SpaceMemberRepository spaceMemberRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private SpaceRepository spaceRepository;
    @InjectMocks
    private SpaceMemberService spaceMemberService;

    @DisplayName("유저가 스페이스를 나가면 SpaceMember 상태가 exit 으로 바뀐다.")
    @Test
    void user_exit_space() {
        // given
        User spaceCreator = UserFixtures.createUser();
        User attendee = UserFixtures.createUser("attendee", "attendee@test.com");
        Space space = SpaceFixtures.createSpace(spaceCreator);
        SpaceMember spaceMember = SpaceMember.of(space, attendee);
        given(userRepository.getById(any())).willReturn(attendee);
        given(spaceRepository.getById(any())).willReturn(space);
        given(spaceMemberRepository.findBySpaceAndUser(any(), any())).willReturn(Optional.of(spaceMember));

        // when
        spaceMemberService.exit(attendee.getId(), space.getId());

        // then
        assertThat(spaceMember.getState()).isEqualTo(SpaceMemberState.EXIT);
    }
}
