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
        Book book = Book.of(
                request.getPostTitle(),
                request.getPostContent(),
                request.getStar(),
                request.isShare(),
                userRepository.getById(userId),
                request.getTopic(),
                request.getImageUrl());
        return postRepository.save(book).getId();
    }

    @Transactional
    public Long registerMediaPost(Long userId, PostRegisterRequest request) {
        Media media = Media.of(
                request.getPostTitle(),
                request.getPostContent(),
                request.getStar(),
                request.isShare(),
                userRepository.getById(userId),
                request.getTopic(),
                request.getImageUrl());
        return postRepository.save(media).getId();
    }

    @Transactional
    public Long registerDisplayPost(Long userId, PostRegisterRequest request) {
        Display display = Display.of(
                request.getPostTitle(),
                request.getPostContent(),
                request.getStar(),
                request.isShare(),
                userRepository.getById(userId),
                request.getTopic(),
                request.getLocation(),
                request.getImageUrl()
        );
        return postRepository.save(display).getId();
    }
}
