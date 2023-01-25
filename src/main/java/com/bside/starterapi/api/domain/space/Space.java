package com.bside.starterapi.api.domain.space;

import com.bside.starterapi.api.domain.user.User;
import com.bside.starterapi.support.domain.BaseAggregateRoot;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "space")
public class Space extends BaseAggregateRoot<Long> {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private SpaceColorTheme theme;

    private String name;

    private String code;

    @Builder
    protected Space(User user, SpaceColorTheme theme, String name, String code) {
        this.user = user;
        this.theme = theme;
        this.name = name;
        this.code = code;
    }

    protected Space() {
    }
}
