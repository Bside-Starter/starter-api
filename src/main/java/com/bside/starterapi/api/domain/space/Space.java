package com.bside.starterapi.api.domain.space;

import com.bside.starterapi.api.domain.spacemember.SpaceMember;
import com.bside.starterapi.api.domain.user.User;
import com.bside.starterapi.support.domain.BaseAggregateRoot;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Entity
@Table(name = "space")
public class Space extends BaseAggregateRoot<Long> {
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    private SpaceColorTheme theme;

    private String name;

    private String code;

    @OneToMany(mappedBy = "space", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SpaceMember> spaceMembers = new ArrayList<>();

    private SpaceState state = SpaceState.VALID;

    protected Space(User user, SpaceColorTheme theme, String name, String code) {
        this.user = user;
        this.theme = theme;
        this.name = name;
        this.code = code;
    }

    protected Space() {
    }

    public static Space of(User user, SpaceColorTheme theme, String name, String code) {
        return new Space(user, theme, name, code);
    }

    public List<User> getAttendees() {
        return spaceMembers.stream()
                .map(SpaceMember::getUser)
                .collect(Collectors.toList());
    }

    public void addAttendee(SpaceMember attendee) {
        spaceMembers.add(attendee);
        attendee.setSpace(this);
    }

    public void delete() {
        this.state = SpaceState.DELETE;
    }
}
