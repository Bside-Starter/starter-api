package com.bside.starterapi.domain.space;

import com.bside.starterapi.api.domain.space.Space;
import com.bside.starterapi.api.domain.space.SpaceRepository;
import com.bside.starterapi.api.domain.space.SpaceState;
import com.bside.starterapi.api.domain.user.User;
import com.bside.starterapi.support.fixtures.SpaceFixtures;
import com.bside.starterapi.support.fixtures.UserFixtures;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Objects;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@ActiveProfiles("test")
public class SpaceRepositoryTest {

    @Autowired
    private SpaceRepository spaceRepository;

    @BeforeEach
    void before_each() {
        spaceRepository.deleteAll();
    }

    @DisplayName("유저가 스페이스를 만들 수 있다")
    @Test
    void create_space() {
        User user = UserFixtures.createUser();
        Space space = SpaceFixtures.createSpace(user);
        spaceRepository.save(space);

        Space findSpace = spaceRepository.getById(Objects.requireNonNull(space.getId()));

        assertThat(findSpace.getName()).isEqualTo(space.getName());
        assertThat(findSpace.getUser().getUsername()).isEqualTo(user.getUsername());
    }

    @DisplayName("유저가 여러 스페이스를 만들면 최초 VALID 상태이다")
    @Test
    void save_get_many_space() {
        User user = UserFixtures.createUser();
        Space space1 = SpaceFixtures.createSpace(user);
        Space space2 = SpaceFixtures.createSpace(user);
        spaceRepository.saveAll(List.of(space1, space2));

        List<Space> spaces = spaceRepository.findAllByUserAndState(user, SpaceState.VALID);

        assertThat(spaces.size()).isEqualTo(2);
    }


}
