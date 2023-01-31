package com.bside.starterapi.api.domain.spacemember;

import com.bside.starterapi.api.domain.space.Space;
import com.bside.starterapi.api.domain.user.User;
import com.bside.starterapi.support.domain.BaseRepository;

import java.util.Optional;

public interface SpaceMemberRepository extends BaseRepository<SpaceMember, Long> {
    Optional<SpaceMember> findBySpaceAndUser(Space space, User attendee);
}
