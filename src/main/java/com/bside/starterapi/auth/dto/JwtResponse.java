package com.bside.starterapi.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class JwtResponse {
    private String accessToken;
    private final String tokenType = "Bearer";
    private String refreshToken;
    private Long userId;
    private String username;
    private String email;
    private List<String> roles;
}
