package com.bside.starterapi.application.authentication;


import com.bside.starterapi.application.authentication.infoImpl.GoogleOAuth2UserInfo;
import com.bside.starterapi.application.authentication.infoImpl.KakaoOAuth2UserInfo;
import com.bside.starterapi.application.authentication.infoImpl.NaverOAuth2UserInfo;

import java.util.Map;

public class OAuth2UserInfoFactory {
    public static OAuth2UserInfo getOAuth2UserInfo(ProviderType providerType, Map<String, Object> attributes) {
        switch (providerType) {
            case GOOGLE: return new GoogleOAuth2UserInfo(attributes);
//            case FACEBOOK: return new FacebookOAuth2UserInfo(attributes);
            case NAVER: return new NaverOAuth2UserInfo(attributes);
            case KAKAO: return new KakaoOAuth2UserInfo(attributes);
            default: throw new IllegalArgumentException("Invalid Provider Type.");
        }
    }
}
