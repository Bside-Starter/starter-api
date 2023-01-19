package com.bside.starterapi.api.domain.post.display;

import com.bside.starterapi.api.domain.post.Post;
import com.bside.starterapi.api.domain.post.PostType;
import com.bside.starterapi.api.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Getter
@Entity
@DiscriminatorValue(PostType.Values.DISPLAY)
public class Display extends Post {
    private String displayTitle = "";

    private String location = "";

    @Builder
    protected Display(String title,
                      String content,
                      Float star,
                      boolean isShare,
                      User user,
                      @NonNull String displayTitle,
                      String location,
                      String imageUrl) {
        super(title, content, star, isShare, user, imageUrl);
        this.displayTitle = displayTitle;
        this.location = location;
    }

    protected Display() {
    }
}
