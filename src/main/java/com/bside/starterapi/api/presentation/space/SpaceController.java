package com.bside.starterapi.api.presentation.space;

import com.bside.starterapi.api.application.auth.AuthenticationFacade;
import com.bside.starterapi.api.application.space.SpaceCreateService;
import com.bside.starterapi.api.application.space.SpaceService;
import com.bside.starterapi.api.application.spacemember.SpaceMemberService;
import com.bside.starterapi.api.presentation.space.dto.SpaceCreateRequest;
import com.bside.starterapi.api.presentation.space.dto.SpaceJoinRequest;
import com.bside.starterapi.support.presentation.StatusDataResult;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@PreAuthorize("hasRole('USER')")
@RestController
@RequestMapping("/api/spaces")
public class SpaceController {
    private final SpaceCreateService spaceCreateService;
    private final SpaceMemberService spaceMemberService;
    private final SpaceService spaceService;
    private final AuthenticationFacade facade;

    @PostMapping
    public ResponseEntity<?> create(SpaceCreateRequest request) {
        return ResponseEntity.ok(StatusDataResult.success(spaceCreateService.create(facade.getUserId(), request)));
    }

    @PostMapping("/{spaceId}/exit")
    public ResponseEntity<?> exitSpace(@PathVariable Long spaceId) {
        return ResponseEntity.ok(StatusDataResult.success(spaceMemberService.exit(facade.getUserId(), spaceId)));
    }

    @PostMapping("/join")
    public ResponseEntity<?> joinSpace(@RequestBody @Valid SpaceJoinRequest request) {
        return ResponseEntity.ok(StatusDataResult.success(spaceService.join(facade.getUserId(), request)));
    }

    @GetMapping("/my")
    public ResponseEntity<?> getMySpaces() {
        return ResponseEntity.ok(spaceService.getMySpaces(facade.getUserId()));
    }
}
