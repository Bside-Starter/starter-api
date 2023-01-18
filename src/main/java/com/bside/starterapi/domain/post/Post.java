package com.bside.starterapi.domain.post;

import com.bside.support.domain.BaseAggregateRoot;
import lombok.Getter;

import javax.persistence.Entity;

@Getter
@Entity
public class Post extends BaseAggregateRoot<Long> {
    private  String title;

    public void updateTitle(String title) {
        this.title = title;
        registerEvent(new TitleUpdatedEvent(this));
    }

    public Post() {
    }

    public Post(String title) {
        this.title = title;
    }
}
