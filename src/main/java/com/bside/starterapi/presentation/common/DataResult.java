package com.bside.starterapi.presentation.common;

import lombok.Getter;

@Getter
public class DataResult<T> {
    private final T data;

    public DataResult(T data) {
        this.data = data;
    }
}
