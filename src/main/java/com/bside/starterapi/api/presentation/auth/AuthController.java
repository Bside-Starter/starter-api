package com.bside.starterapi.api.presentation.auth;

import com.bside.starterapi.api.application.auth.AuthService;
import com.bside.starterapi.api.application.auth.RefreshTokenService;
import com.bside.starterapi.api.presentation.auth.dto.*;
import com.bside.starterapi.support.presentation.StatusDataResult;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;
    private final RefreshTokenService refreshTokenService;

    @PostMapping("/signin")
    public ResponseEntity<StatusDataResult<JwtResponse>> generateToken(@Valid @RequestBody AuthenticateUserRequest request) {
        JwtResponse response = authService.generateTokenBySignIn(request);
        return ResponseEntity.ok(StatusDataResult.success(response));
    }

    @PostMapping("/signup")
    public ResponseEntity<StatusDataResult<Long>> registerUser(@Valid @RequestBody RegisterUserRequest request) {
        return ResponseEntity.ok(StatusDataResult.success(authService.registerUser(request)));
    }

    @PostMapping("/refreshtoken")
    public ResponseEntity<StatusDataResult<TokenRefreshResponse>> refreshToken(@Valid @RequestBody TokenRefreshRequest request) {
        return ResponseEntity.ok(StatusDataResult.success(refreshTokenService.refresh(request)));
    }
}
