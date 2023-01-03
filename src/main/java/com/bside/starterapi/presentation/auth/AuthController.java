package com.bside.starterapi.presentation.auth;

import com.bside.starterapi.application.AuthService;
import com.bside.starterapi.presentation.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/sign-in")
    public ResponseEntity<ApiResponse<JwtResponse>> generateToken(@RequestBody AuthenticateUserRequest request) {
        JwtResponse response = authService.generateTokenBySignIn(request);
        return ResponseEntity.ok(ApiResponse.success(response));
    }

    @PostMapping("/sign-up")
    public ResponseEntity<ApiResponse<Long>> registerUser(@RequestBody RegisterUserRequest request) {
        return ResponseEntity.ok(ApiResponse.success(authService.registerUser(request)));
    }
}
