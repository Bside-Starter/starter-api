package com.bside.starterapi.support.fixtures;

import com.bside.starterapi.api.domain.user.User;

public class UserFixtures {
    public static User createUser() {
        return User.create("홍길동", "gildong@test.com", "password123", "nickname");
    }
}
