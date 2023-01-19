package com.bside.starterapi.api.application.post;

import com.bside.starterapi.api.domain.post.PostRepository;
import com.bside.starterapi.api.domain.post.book.Book;
import com.bside.starterapi.api.domain.post.display.Display;
import com.bside.starterapi.api.domain.post.media.Media;
import com.bside.starterapi.api.domain.user.UserRepository;
import com.bside.starterapi.api.presentation.post.dto.PostRegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PostRegisterService {
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    @Transactional
    public Long registerBookPost(Long userId, PostRegisterRequest request) {
        Book book = Book.builder()
                .user(userRepository.getById(userId))
                .bookTitle(request.getTopic())
                .title(request.getPostTitle())
                .content(request.getPostContent())
                .isShare(request.isShare())
                .star(request.getStar())
                .imageUrl(request.getImageUrl())
                .build();
        return postRepository.save(book).getId();
    }

    @Transactional
    public Long registerMediaPost(Long userId, PostRegisterRequest request) {
        Media media = Media.builder()
                .user(userRepository.getById(userId))
                .mediaTitle(request.getTopic())
                .title(request.getPostTitle())
                .content(request.getPostContent())
                .isShare(request.isShare())
                .star(request.getStar())
                .imageUrl(request.getImageUrl())
                .build();
        return postRepository.save(media).getId();
    }

    @Transactional
    public Long registerDisplayPost(Long userId, PostRegisterRequest request) {
        Display display = Display.builder()
                .user(userRepository.getById(userId))
                .displayTitle(request.getTopic())
                .location(request.getLocation())
                .title(request.getPostTitle())
                .content(request.getPostContent())
                .isShare(request.isShare())
                .imageUrl(request.getImageUrl())
                .star(request.getStar())
                .build();
        return postRepository.save(display).getId();
    }
}
