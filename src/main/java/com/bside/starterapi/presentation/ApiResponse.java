package com.bside.starterapi.presentation;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ApiResponse<T> {
    private final String message;
    private final T body;

    public static <T> ApiResponse<T> error(String message) {
        return new ApiResponse<>(
                message,
                null
        );
    }

    public static <T> ApiResponse<T> success(T body) {
        return new ApiResponse<>(
                "",
                body
        );
    }
}
