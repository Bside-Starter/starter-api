package com.bside.starterapi.api.domain.user;

import com.bside.starterapi.support.domain.BaseRepository;

import java.util.Optional;

public interface UserRepository extends BaseRepository<User, Long> {

    Optional<User> findByEmail(String email);

    Boolean existsByEmail(String email);
}
