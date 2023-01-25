package com.bside.starterapi.domain.group;

import com.bside.starterapi.api.domain.group.Group;
import com.bside.starterapi.api.domain.group.GroupColorTheme;
import com.bside.starterapi.api.domain.group.GroupRepository;
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
public class GroupRepositoryTest {

    @Autowired
    private GroupRepository groupRepository;

    @BeforeEach
    void before_each() {
        groupRepository.deleteAll();
    }

    @DisplayName("유저가 그룹을 만들 수 있다")
    @Test
    void create_group() {
        User user = createUser();
        Group group = Group.builder()
                .code("AB12345678")
                .user(user)
                .name("소근방")
                .theme(GroupColorTheme.BLUE)
                .build();
        groupRepository.save(group);

        Group findGroup = groupRepository.getById(Objects.requireNonNull(group.getId()));

        assertThat(findGroup.getName()).isEqualTo(group.getName());
        assertThat(findGroup.getUser().getUsername()).isEqualTo(user.getUsername());
    }

    private User createUser() {
        return User.create("홍길동", "gildong@test.com", "password123", "nickname");
    }
}
