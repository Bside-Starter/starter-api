package com.bside.starterapi.application;

public class AlreadyExistsUsernameException extends RuntimeException {
    public AlreadyExistsUsernameException() {
        super("이미 존재하는 유저 아이디입니다.");
    }
}
