package com.bside.starterapi.api.domain.post.book;

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
@DiscriminatorValue(PostType.Values.BOOK)
public class Book extends Post {
    private String bookTitle = "";

    @Builder
    protected Book(String title,
                   String content,
                   Float star,
                   boolean isShare,
                   User user,
                   @NonNull String bookTitle,
                   String imageUrl) {
        super(title, content, star, isShare, user, imageUrl);
        this.bookTitle = bookTitle;
    }

    protected Book() {
    }
}
