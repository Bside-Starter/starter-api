package com.bside.starterapi.api.presentation.post;

import com.bside.starterapi.api.application.auth.AuthenticationFacade;
import com.bside.starterapi.api.application.post.PostRegisterService;
import com.bside.starterapi.api.presentation.post.dto.PostRegisterRequest;
import com.bside.starterapi.support.presentation.StatusDataResult;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/posts")
public class PostRegisterController {
    private final PostRegisterService postRegisterService;
    private final AuthenticationFacade facade;

    @PostMapping("/books")
    private ResponseEntity<StatusDataResult<Long>> registerBookPost(@RequestBody @Valid PostRegisterRequest request) {
        return ResponseEntity.ok(StatusDataResult.success(postRegisterService.registerBookPost(facade.getUserId(), request)));
    }

    @PostMapping("/media")
    private ResponseEntity<StatusDataResult<Long>> registerMediaPost(@RequestBody @Valid PostRegisterRequest request) {
        return ResponseEntity.ok(StatusDataResult.success(postRegisterService.registerMediaPost(facade.getUserId(), request)));
    }

    @PostMapping("/display")
    private ResponseEntity<StatusDataResult<Long>> registerDisplayPost(@RequestBody @Valid PostRegisterRequest request) {
        return ResponseEntity.ok(StatusDataResult.success(postRegisterService.registerDisplayPost(facade.getUserId(), request)));
    }
}
