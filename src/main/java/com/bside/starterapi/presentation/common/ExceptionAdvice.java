package com.bside.starterapi.presentation.common;

import com.bside.starterapi.application.auth.AlreadyExistsEmailException;
import com.bside.starterapi.application.auth.TokenRefreshException;
import com.bside.starterapi.domain.user.RolesNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionAdvice {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler
    public ErrorResponse handleAlreadyExistsEmailException(AlreadyExistsEmailException e) {
        return new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                e.getMessage()
        );
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler
    public ErrorResponse handleRolesNotFoundException(RolesNotFoundException e) {
        return new ErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                e.getMessage()
        );
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler
    public ErrorResponse handleTokenRefreshException(TokenRefreshException e) {
        return new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                e.getMessage()
        );
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler
    public ErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        ValidErrorResponse errorResponse = new ValidErrorResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        e.getFieldErrors()
                .forEach(fe ->
                        errorResponse.addValidation(fe.getField(), fe.getDefaultMessage()));
        return errorResponse;
    }
}
