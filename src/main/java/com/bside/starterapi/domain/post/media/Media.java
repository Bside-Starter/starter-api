package com.bside.starterapi.domain.post.media;

import com.bside.starterapi.domain.post.Post;
import com.bside.starterapi.domain.post.PostType;
import com.bside.starterapi.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Getter
@Entity
@DiscriminatorValue(PostType.Values.MEDIA)
public class Media extends Post {

    private String mediaTitle;

    @Builder
    protected Media(String title,
                    String content,
                    Float star,
                    boolean isShare,
                    User user,
                    @NonNull String mediaTitle) {
        super(title, content, star, isShare, user);
        this.mediaTitle = mediaTitle;
    }

    protected Media() {

    }
}
