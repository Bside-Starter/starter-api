package com.bside.starterapi.presentation;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.Set;

@Getter
@RequiredArgsConstructor
public class RegisterUserRequest {
    @NotBlank
    private final String username;
    @NotBlank
    private final String email;
    @NotBlank
    private final String password;
    private final Set<String> role;
}
