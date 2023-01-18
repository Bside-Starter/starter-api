package com.bside.starterapi.domain.post;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class TitleUpdatedEvent {
    private final Post post;
}

