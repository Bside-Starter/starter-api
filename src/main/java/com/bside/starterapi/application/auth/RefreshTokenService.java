package com.bside.starterapi.application.auth;

import com.bside.starterapi.presentation.auth.dto.TokenRefreshRequest;
import com.bside.starterapi.presentation.auth.dto.TokenRefreshResponse;
import com.bside.starterapi.config.auth.JwtProperties;
import com.bside.starterapi.config.auth.JwtUtils;
import com.bside.starterapi.domain.auth.RefreshToken;
import com.bside.starterapi.domain.auth.RefreshTokenRepository;
import com.bside.starterapi.domain.user.User;
import com.bside.starterapi.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class RefreshTokenService {
    private final JwtProperties jwtProperties;
    private final RefreshTokenRepository refreshTokenRepository;
    private final UserRepository userRepository;
    private final JwtUtils jwtUtils;

    @Transactional
    public RefreshToken createRefreshToken(Long userId) {
        User user = userRepository.findById(userId).orElseThrow();
        RefreshToken refreshToken = RefreshToken.builder()
                .user(user)
                .expiryDate(Instant.now().plusMillis(jwtProperties.getRefreshExpirationMs()))
                .token(UUID.randomUUID().toString())
                .build();
        return refreshTokenRepository.save(refreshToken);
    }

    @Transactional
    public Long deleteByUserId(Long userId) {
        return refreshTokenRepository.deleteByUser(
                userRepository.findById(userId).orElseThrow()
        );
    }

    @Transactional
    public TokenRefreshResponse refresh(TokenRefreshRequest request) {
        return findByToken(request.getRefreshToken())
                .map(this::verifyExpiration)
                .map(RefreshToken::getUser)
                .map(user -> new TokenRefreshResponse(
                        jwtUtils.generateJwtFromUsername(user.getUsername()),
                        request.getRefreshToken()))
                .orElseThrow(() -> new TokenRefreshException(request.getRefreshToken(),
                        "Refresh Token이 Database에 존재하지 않습니다."));
    }

    private Optional<RefreshToken> findByToken(String refreshToken) {
        return refreshTokenRepository.findByToken(refreshToken);
    }

    private RefreshToken verifyExpiration(RefreshToken token) {
        if (token.getExpiryDate().isBefore(Instant.now())) {
            refreshTokenRepository.delete(token);
            throw new TokenRefreshException(token.getToken(), "Refresh Token이 만료되었습니다.");
        }
        return token;
    }
}
