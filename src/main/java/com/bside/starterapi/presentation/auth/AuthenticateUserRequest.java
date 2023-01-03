package com.bside.starterapi.presentation.auth;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class AuthenticateUserRequest {
    private final String username;
    private final String password;
}
