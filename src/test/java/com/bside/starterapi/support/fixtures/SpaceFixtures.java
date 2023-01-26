package com.bside.starterapi.support.fixtures;

import com.bside.starterapi.api.domain.space.Space;
import com.bside.starterapi.api.domain.space.SpaceColorTheme;
import com.bside.starterapi.api.domain.user.User;

public class SpaceFixtures {
    public static Space createSpace(User user) {
        return Space.builder()
                .code("AB12345678")
                .user(user)
                .name("소근방")
                .theme(SpaceColorTheme.BLUE)
                .build();
    }
}
