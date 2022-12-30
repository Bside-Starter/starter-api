package com.bside.starterapi.application.authentication;

import lombok.Getter;

@Getter
public enum ProviderType {
    GOOGLE,
    FACEBOOK,
    NAVER,
    KAKAO,
    LOCAL;
}
