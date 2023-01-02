package com.bside.starterapi.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);

    default Role findByNameOrThrow(ERole name) {
        return findByName(name).orElseThrow(RolesNotFoundException::new);
    }
}
