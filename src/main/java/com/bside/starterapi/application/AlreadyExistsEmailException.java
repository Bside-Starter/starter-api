package com.bside.starterapi.application;

public class AlreadyExistsEmailException extends RuntimeException {
    public AlreadyExistsEmailException() {
        super("이미 존재하는 이메일입니다.");
    }
}
