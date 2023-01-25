package com.bside.starterapi.api.presentation.space.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SpaceCreateResponse {
    private Long spaceId;
    private String spaceCode;
}
