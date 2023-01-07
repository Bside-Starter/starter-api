package com.bside.starterapi.presentation.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticateUserRequest {
    @NotBlank
    private String username;
    @NotBlank
    private String password;
}
