package com.bside.starterapi.domain.space;

import com.bside.starterapi.api.domain.space.Space;
import com.bside.starterapi.api.domain.space.SpaceColorTheme;
import com.bside.starterapi.api.domain.space.SpaceRepository;
import com.bside.starterapi.api.domain.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

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
        User user = createUser();
        Space space = Space.builder()
                .code("AB12345678")
                .user(user)
                .name("소근방")
                .theme(SpaceColorTheme.BLUE)
                .build();
        spaceRepository.save(space);

        Space findSpace = spaceRepository.getById(Objects.requireNonNull(space.getId()));

        assertThat(findSpace.getName()).isEqualTo(space.getName());
        assertThat(findSpace.getUser().getUsername()).isEqualTo(user.getUsername());
    }

    private User createUser() {
        return User.create("홍길동", "gildong@test.com", "password123", "nickname");
    }
}
