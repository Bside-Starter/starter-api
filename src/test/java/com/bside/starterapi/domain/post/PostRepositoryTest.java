package com.bside.starterapi.domain.post;

import com.bside.starterapi.api.domain.post.Post;
import com.bside.starterapi.api.domain.post.PostRepository;
import com.bside.starterapi.api.domain.post.book.Book;
import com.bside.starterapi.api.domain.post.book.BookRepository;
import com.bside.starterapi.api.domain.user.User;
import com.bside.starterapi.support.fixtures.UserFixtures;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
@Import(PostRepositoryTestConfig.class)
class PostRepositoryTest {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private BookRepository bookRepository;

    @BeforeEach
    void before_each() {
        postRepository.deleteAll();
    }

    @DisplayName("책 포스팅이 정상 저장된다")
    @Test
    void post_save() {
        Book book = createBook(UserFixtures.createUser());
        postRepository.save(book);

        Book findBook = bookRepository.getById(Objects.requireNonNull(book.getId()));

        assertThat(postRepository.findAll().size()).isEqualTo(1);
        assertThat(findBook.getBookTitle()).isEqualTo("노인과 바다");
    }

    @DisplayName("포스팅 내용이 정상 수정된다")
    @Test
    void post_title_update() {
        Post post = createBook(UserFixtures.createUser());
        postRepository.save(post);

        post.updateTitle("new Title");
        postRepository.save(post); // 도메인이벤트를 발행하기 위해선 save.. method를 사용해주어야 함
        Post findPost = postRepository.getById(Objects.requireNonNull(post.getId()));


        assertThat(postRepository.findAll().size()).isEqualTo(1);
        assertThat(findPost.getTitle()).isEqualTo("new Title");
    }


    private Book createBook(User user) {
        return Book.of("안보면 후회해요",
                "이 책 정말 감명깊습니다.",
                5.0F,
                true,
                user,
                "노인과 바다",
                "url.xtt");
    }
}
