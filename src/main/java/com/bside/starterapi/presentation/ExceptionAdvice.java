package com.bside.starterapi.presentation;

import com.bside.starterapi.application.AlreadyExistsEmailException;
import com.bside.starterapi.application.AlreadyExistsUsernameException;
import com.bside.starterapi.domain.user.RolesNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ExceptionAdvice extends ResponseEntityExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<ApiResponse<Void>> handleAlreadyExistsEmailException(AlreadyExistsEmailException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse.error(e.getMessage()));
    }

    @ExceptionHandler
    public ResponseEntity<ApiResponse<Void>> handleAlreadyExistsUsernameException(AlreadyExistsUsernameException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse.error(e.getMessage()));
    }

    @ExceptionHandler
    public ResponseEntity<ApiResponse<Void>> handleRolesNotFoundException(RolesNotFoundException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiResponse.error(e.getMessage()));
    }
}
