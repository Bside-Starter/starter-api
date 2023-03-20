package com.bside.starterapi.support.presentation;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class StatusDataResult<S, T> {
    private final S status;
    private final T data;

    public static <T> StatusDataResult<CommonStatus, T> success(T body) {
        return new StatusDataResult<>(
                CommonStatus.SUCCESS,
                body
        );
    }
}
