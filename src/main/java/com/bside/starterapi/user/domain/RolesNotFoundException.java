package com.bside.starterapi.user.domain;

public class RolesNotFoundException extends RuntimeException {
    public RolesNotFoundException() {
        super("Role이 존재하지 않습니다.");
    }
}