package com.bside.starterapi.api.presentation.space.dto;

import com.bside.starterapi.api.domain.space.Space;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SpaceResponse {
    private Long spaceId;
    private String profile;
    private String name;
    private String code;
    private List<AttendeesResponse> attendees;

    public static SpaceResponse from(Space space) {
        return new SpaceResponse(
                space.getId(),
                space.getUser().getProfile(),
                space.getName(),
                space.getCode(),
                space.getAttendees()
                        .stream()
                        .map(AttendeesResponse::from)
                        .collect(Collectors.toList())
        );
    }
}
