package com.bside.starterapi.common.presentation;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public class ValidErrorResponse extends ErrorResponse {
    private final Map<String, String> validation = new HashMap<>();

    public ValidErrorResponse(int code, String message) {
        super(code, message);
    }

    public void addValidation(String fieldName, String errorMessage) {
        this.validation.put(fieldName, errorMessage);
    }

}
