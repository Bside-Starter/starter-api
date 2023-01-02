package com.bside.starterapi.domain.user;

public class RolesNotFoundException extends RuntimeException {
    public RolesNotFoundException() {
        super("Role이 존재하지 않습니다.");
    }
}