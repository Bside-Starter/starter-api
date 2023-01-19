package com.bside.starterapi.api.application.auth;

import com.bside.starterapi.api.config.auth.UserDetailsImpl;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationFacade {

    public Long getUserId() {
        return getUserDetails().getId();
    }

    private UserDetailsImpl getUserDetails() {
        return (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication();
    }
}
