package com.bside.starterapi.api.presentation.space;

import com.bside.starterapi.api.application.auth.AuthenticationFacade;
import com.bside.starterapi.api.application.space.SpaceCreateService;
import com.bside.starterapi.api.application.spacemember.SpaceMemberService;
import com.bside.starterapi.api.presentation.space.dto.SpaceCreateRequest;
import com.bside.starterapi.support.presentation.StatusDataResult;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/space")
public class SpaceController {
    private final SpaceCreateService spaceCreateService;
    private final SpaceMemberService spaceMemberService;
    private final AuthenticationFacade facade;

    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> create(SpaceCreateRequest request) {
        return ResponseEntity.ok(StatusDataResult.success(spaceCreateService.create(facade.getUserId(), request)));
    }

    @PostMapping("/{spaceId}/exit")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> exitSpace(@PathVariable Long spaceId) {
        return ResponseEntity.ok(StatusDataResult.success(spaceMemberService.exit(facade.getUserId(), spaceId)));
    }
}
