package com.bside.starterapi.api.application.space;

import com.bside.starterapi.api.domain.space.Space;
import com.bside.starterapi.api.domain.space.SpaceRepository;
import com.bside.starterapi.api.domain.space.SpaceState;
import com.bside.starterapi.api.domain.user.User;
import com.bside.starterapi.support.fixtures.SpaceFixtures;
import com.bside.starterapi.support.fixtures.UserFixtures;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class SpaceServiceTest {
    @Mock
    private SpaceRepository spaceRepository;
    @InjectMocks
    private SpaceService spaceService;

    @DisplayName("방장이 스페이스를 삭제할 수 있다.")
    @Test
    void user_can_delete_space() {
        // given
        User user = UserFixtures.createUser();
        Space space = SpaceFixtures.createSpace(user);
        given(spaceRepository.getById(any())).willReturn(space);

        // when
        spaceService.delete(user.getId(), space.getId());

        // then
        assertThat(space.getState()).isEqualTo(SpaceState.DELETE);
    }

    @DisplayName("타 유저는 스페이스를 삭제할 수 없다.")
    @Test
    void other_user_can_not_delete_space() {
        // given
        User user = UserFixtures.createUser();
        Space space = SpaceFixtures.createSpace(user);
        given(spaceRepository.getById(any())).willReturn(space);

        // when & then
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> spaceService.delete(Long.MAX_VALUE, space.getId()));
    }
}