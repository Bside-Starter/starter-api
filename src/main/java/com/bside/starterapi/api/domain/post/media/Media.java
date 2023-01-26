package com.bside.starterapi.api.domain.post.media;

import com.bside.starterapi.api.domain.post.Post;
import com.bside.starterapi.api.domain.post.PostType;
import com.bside.starterapi.api.domain.user.User;
import lombok.Getter;
import lombok.NonNull;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Entity
@Table(name = "media")
@DiscriminatorValue(PostType.Values.MEDIA)
public class Media extends Post {
    private String mediaTitle = "";

    public static Media of(String title,
                           String content,
                           Float star,
                           boolean isShare,
                           User user,
                           @NonNull String mediaTitle,
                           String imageUrl) {
        return new Media(title, content, star, isShare, user, mediaTitle, imageUrl);
    }

    protected Media(String title,
                    String content,
                    Float star,
                    boolean isShare,
                    User user,
                    @NonNull String mediaTitle,
                    String imageUrl) {
        super(title, content, star, isShare, user, imageUrl);
        this.mediaTitle = mediaTitle;
    }

    protected Media() {
    }
}
