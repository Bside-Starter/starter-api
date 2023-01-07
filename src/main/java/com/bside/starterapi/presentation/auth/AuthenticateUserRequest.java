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
    private String email;
    @NotBlank
    private String password;
}
