package com.bside.starterapi.api.presentation.space.dto;

import com.bside.starterapi.api.domain.space.SpaceColorTheme;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SpaceCreateRequest {
    private String name;
    private SpaceColorTheme theme;
}
