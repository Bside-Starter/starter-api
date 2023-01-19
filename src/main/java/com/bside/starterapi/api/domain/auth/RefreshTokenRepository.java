package com.bside.starterapi.api.domain.auth;

import com.bside.starterapi.api.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByToken(String token);

    Long deleteByUser(User user);
}
