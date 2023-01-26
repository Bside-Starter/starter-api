package com.bside.starterapi.api.domain.post.book;

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
@Table(name = "book")
@DiscriminatorValue(PostType.Values.BOOK)
public class Book extends Post {
    private String bookTitle = "";

    public static Book of(String title,
                          String content,
                          Float star,
                          boolean isShare,
                          User user,
                          @NonNull String bookTitle,
                          String imageUrl) {
        return new Book(title, content, star, isShare, user, bookTitle, imageUrl);
    }

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
