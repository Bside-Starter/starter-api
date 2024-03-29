package com.bside.starterapi.api.domain.user;

import com.bside.starterapi.support.domain.BaseAggregateRoot;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "users",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "email")
        })
public class User extends BaseAggregateRoot<Long> {

    private String username;

    private String email;

    private String password;

    private String nickname;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    private String profile;

    protected User(String username, String email, String password, String nickname) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.nickname = nickname;
    }

    public static User create(String username, String email, String password, String nickname) {
        return new User(username,
                email,
                password,
                nickname
        );
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
