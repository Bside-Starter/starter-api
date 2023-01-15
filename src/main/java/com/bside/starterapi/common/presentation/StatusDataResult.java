package com.bside.starterapi.common.presentation;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class StatusDataResult<T> {
    private final CommonStatus status;
    private final T data;

    public static <T> StatusDataResult<T> success(T body) {
        return new StatusDataResult<>(
                CommonStatus.SUCCESS,
                body
        );
    }
}
