package com.bside.starterapi.api.domain.spacemember;

import com.bside.starterapi.api.domain.space.Space;
import com.bside.starterapi.api.domain.user.User;
import com.bside.starterapi.support.domain.BaseAggregateRoot;
import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "space_member")
public class SpaceMember extends BaseAggregateRoot<Long> {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "space_id")
    private Space space;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private SpaceMemberState state = SpaceMemberState.VALID;

    public void setSpace(Space space) {
        this.space = space;
    }

    public static SpaceMember of(Space space, User user) {
        return new SpaceMember(space, user);
    }

    protected SpaceMember(Space space, User user) {
        this.space = space;
        this.user = user;
    }

    protected SpaceMember() {
    }

    public void exit() {
        this.state = SpaceMemberState.EXIT;
    }
}
