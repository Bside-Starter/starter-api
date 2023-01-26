package com.bside.starterapi.support.fixtures;

import com.bside.starterapi.api.domain.space.Space;
import com.bside.starterapi.api.domain.space.SpaceColorTheme;
import com.bside.starterapi.api.domain.user.User;

public class SpaceFixtures {
    public static Space createSpace(User user) {
        return Space.of(
                user,
                SpaceColorTheme.BLACK,
                "소근방",
                "AB12345678");
    }
}
