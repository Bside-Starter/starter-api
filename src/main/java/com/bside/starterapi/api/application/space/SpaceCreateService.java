package com.bside.starterapi.api.application.space;

import com.bside.starterapi.api.domain.space.Space;
import com.bside.starterapi.api.domain.space.SpaceRepository;
import com.bside.starterapi.api.domain.user.User;
import com.bside.starterapi.api.domain.user.UserRepository;
import com.bside.starterapi.api.presentation.space.dto.SpaceCreateRequest;
import com.bside.starterapi.api.presentation.space.dto.SpaceCreateResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class SpaceCreateService {
    private final UserRepository userRepository;
    private final SpaceRepository spaceRepository;

    @Transactional
    public SpaceCreateResponse create(Long userId, SpaceCreateRequest request) {
        User user = userRepository.getById(userId);
        String code = RandomCodeGenerator.get();
        Space space = Space.builder()
                .user(user)
                .theme(request.getTheme())
                .code(code)
                .name(request.getName())
                .build();
        return new SpaceCreateResponse(spaceRepository.save(space).getId(), code);
    }
}
