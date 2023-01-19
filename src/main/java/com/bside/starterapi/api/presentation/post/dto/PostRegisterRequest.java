package com.bside.starterapi.api.presentation.post.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostRegisterRequest {
    @NotNull
    private String postTitle;
    @NotNull
    private String postContent;
    @NotNull
    private Float star;
    private boolean isShare;
    private String imageUrl;
    @NotNull
    private String topic;
    private String location;
}
