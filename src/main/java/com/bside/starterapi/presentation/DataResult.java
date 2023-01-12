package com.bside.starterapi.presentation;

import lombok.Getter;

@Getter
public class DataResult<T> {
    private final T data;

    public DataResult(T data) {
        this.data = data;
    }
}
