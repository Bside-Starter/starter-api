package com.bside.starterapi.api.domain.space;

import com.bside.starterapi.api.domain.user.User;
import com.bside.starterapi.support.domain.BaseRepository;

import java.util.List;
import java.util.Optional;

public interface SpaceRepository extends BaseRepository<Space, Long> {
    Optional<Space> findByCode(String code);

    List<Space> findAllByUserAndState(User user, SpaceState state);
}
