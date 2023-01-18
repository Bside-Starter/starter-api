package com.bside.starterapi.domain.post;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import(PostRepositoryTestConfig.class)
class PostRepositoryTest {

    @Autowired
    private PostRepository postRepository;

    @BeforeEach
    void before_each() {
        postRepository.deleteAll();
    }

    @DisplayName("포스트가 정상 저장된다")
    @Test
    void post_save() {
        String title = "Test Title";
        Post post = new Post(title);
        postRepository.save(post);

        Post findPost = postRepository.getById(Objects.requireNonNull(post.getId()));

        assertThat(postRepository.findAll().size()).isEqualTo(1);
        assertThat(findPost.getTitle()).isEqualTo("Test Title");
    }

    @DisplayName("포스트 제목이 정상 수정된다")
    @Test
    void post_title_update() {
        String title = "Test Title";
        Post post = new Post(title);
        postRepository.save(post);

        post.updateTitle("new Title");
        postRepository.save(post); // 도메인이벤트를 발행하기 위해선 save.. method를 사용해주어야 함
        Post findPost = postRepository.getById(Objects.requireNonNull(post.getId()));

        assertThat(postRepository.findAll().size()).isEqualTo(1);
        assertThat(findPost.getTitle()).isEqualTo("new Title");
    }
}
