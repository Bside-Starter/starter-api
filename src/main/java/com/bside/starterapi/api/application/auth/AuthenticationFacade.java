package com.bside.starterapi.api.application.auth;

import com.bside.starterapi.api.config.auth.UserDetailsImpl;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationFacade {

    public Long getUserId() {
        UserDetailsImpl userDetails = (UserDetailsImpl) getUserDetails();
        return userDetails.getId();
    }

    private UserDetails getUserDetails() {
        return (UserDetails) SecurityContextHolder.getContext().getAuthentication();
    }
}
