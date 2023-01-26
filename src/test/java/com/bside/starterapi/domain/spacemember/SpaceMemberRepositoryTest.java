package com.bside.starterapi.domain.spacemember;

import com.bside.starterapi.api.domain.space.Space;
import com.bside.starterapi.api.domain.space.SpaceRepository;
import com.bside.starterapi.api.domain.spacemember.SpaceMember;
import com.bside.starterapi.api.domain.user.User;
import com.bside.starterapi.api.domain.user.UserRepository;
import com.bside.starterapi.support.fixtures.SpaceFixtures;
import com.bside.starterapi.support.fixtures.UserFixtures;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
public class SpaceMemberRepositoryTest {
    @Autowired
    private SpaceRepository spaceRepository;
    @Autowired
    private UserRepository userRepository;

    private final User user = UserFixtures.createUser();
    private final Space space = SpaceFixtures.createSpace(user);

    @BeforeEach
    void before_each() {
        userRepository.save(user);
        spaceRepository.save(space);

    }

    @AfterEach
    void after_each() {
        spaceRepository.deleteAll();
    }

    @DisplayName("유저가 코드를 통해 찾은 스페이스에 참여할 수 있다.")
    @Test
    void possible_to_enter_space() {
        // given
        User attendee1 = UserFixtures.createUser("참여자1", "attendee1@test.com");
        User attendee2 = UserFixtures.createUser("참여자2", "attendee2@test.com");
        Space findSpace = spaceRepository.findByCode(space.getCode())
                .orElseThrow();

        SpaceMember spaceMember1 = SpaceMember.of(findSpace, attendee1);
        SpaceMember spaceMember2 = SpaceMember.of(findSpace, attendee2);

        findSpace.addAttendee(spaceMember1);
        findSpace.addAttendee(spaceMember2);

        userRepository.saveAll(List.of(attendee1, attendee2));
        spaceRepository.save(findSpace);
        // when
        List<User> attendees = findSpace.getAttendees();
        // then
        assertThat(attendees.size()).isEqualTo(2);
    }
}
