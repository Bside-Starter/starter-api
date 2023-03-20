package com.bside.starterapi.api.presentation.space.dto;

import com.bside.starterapi.api.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AttendeesResponse {
    private String profile;
    private String name;

    public static AttendeesResponse from(User user) {
        return new AttendeesResponse(
                user.getProfile(),
                user.getUsername()
        );
    }
}
