package com.bside.starterapi.api.domain.post;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class TitleUpdatedEvent {
    private final Post post;
}

