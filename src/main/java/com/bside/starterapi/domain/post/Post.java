package com.bside.starterapi.domain.post;

import com.bside.starterapi.domain.user.User;
import com.bside.support.domain.BaseAggregateRoot;
import lombok.Getter;
import lombok.NonNull;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
public abstract class Post extends BaseAggregateRoot<Long> {
    @NotNull
    private String title;

    @NotNull
    private String content;

    @NotNull
    private Float star;

    @NotNull
    private boolean isShare;

    @Transient
    private PostType type;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @JoinColumn(name = "user_id")
    private User user;

    protected Post() {
    }

    protected Post(@NonNull String title, @NonNull String content, @NonNull Float star, boolean isShare, @NonNull User user) {
        this.title = title;
        this.content = content;
        this.star = star;
        this.isShare = isShare;
        this.user = user;
    }

    public void updateTitle(String title) {
        this.title = title;
        registerEvent(new TitleUpdatedEvent(this));
    }
}
