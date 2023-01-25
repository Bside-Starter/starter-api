package com.bside.starterapi.api.domain.group;

import com.bside.starterapi.api.domain.user.User;
import com.bside.starterapi.support.domain.BaseAggregateRoot;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "groups")
public class Group extends BaseAggregateRoot<Long> {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private GroupColorTheme theme;

    private String name;

    private String code;

    @Builder
    public Group(User user, GroupColorTheme theme, String name, String code) {
        this.user = user;
        this.theme = theme;
        this.name = name;
        this.code = code;
    }

    protected Group() {
    }
}
