package com.bside.starterapi.api.presentation.spacemember;

import com.bside.starterapi.api.application.auth.AuthenticationFacade;
import com.bside.starterapi.api.application.spacemember.SpaceMemberService;
import com.bside.starterapi.support.presentation.StatusDataResult;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/spacemember")
public class SpaceMemberController {
    private final AuthenticationFacade facade;
    private final SpaceMemberService spaceMemberService;

    @PostMapping("/exit/{spaceId}")
    public ResponseEntity<?> exitSpace(@PathVariable Long spaceId) {
        return ResponseEntity.ok(StatusDataResult.success(spaceMemberService.exit(facade.getUserId(), spaceId)));
    }
}
