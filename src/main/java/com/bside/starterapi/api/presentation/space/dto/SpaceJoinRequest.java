package com.bside.starterapi.api.presentation.space.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SpaceJoinRequest {
    @NotNull
    private String code;
}
