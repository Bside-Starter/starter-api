package com.bside.starterapi.api.presentation.post;

import com.bside.starterapi.api.application.auth.AuthenticationFacade;
import com.bside.starterapi.api.application.post.PostRegisterService;
import com.bside.starterapi.api.presentation.post.dto.PostRegisterRequest;
import com.bside.starterapi.support.presentation.StatusDataResult;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts")
public class PostRegisterController {
    private final PostRegisterService postRegisterService;
    private final AuthenticationFacade facade;

    @PostMapping("/books")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> registerBooks(@RequestBody @Valid PostRegisterRequest request) {
        return ResponseEntity.ok(StatusDataResult.success(postRegisterService.registerBookPost(facade.getUserId(), request)));
    }

    @PostMapping("/media")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> registerMedia(@RequestBody @Valid PostRegisterRequest request) {
        postRegisterService.registerMediaPost(facade.getUserId(), request);
        return ResponseEntity.ok(StatusDataResult.success(postRegisterService.registerMediaPost(facade.getUserId(), request)));
    }

    @PostMapping("/display")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> registerDisplay(@RequestBody @Valid PostRegisterRequest request) {
        postRegisterService.registerMediaPost(facade.getUserId(), request);
        return ResponseEntity.ok(StatusDataResult.success(postRegisterService.registerDisplayPost(facade.getUserId(), request)));
    }
}
