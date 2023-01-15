package com.bside.starterapi.auth.application;

import com.bside.starterapi.auth.domain.RefreshToken;
import com.bside.starterapi.auth.dto.AuthenticateUserRequest;
import com.bside.starterapi.auth.dto.JwtResponse;
import com.bside.starterapi.auth.dto.RegisterUserRequest;
import com.bside.starterapi.config.auth.JwtUtils;
import com.bside.starterapi.config.auth.UserDetailsImpl;
import com.bside.starterapi.user.domain.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final RoleRepository roleRepository;
    private final RefreshTokenService refreshTokenService;

    @Transactional
    public JwtResponse generateTokenBySignIn(AuthenticateUserRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        String jwt = jwtUtils.generateJwtFromUsername(userDetails.getEmail());
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        RefreshToken refreshToken = refreshTokenService.createRefreshToken(userDetails.getId());
        return new JwtResponse(
                jwt,
                refreshToken.getToken(),
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles
        );
    }

    @Transactional
    public Long registerUser(RegisterUserRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new AlreadyExistsEmailException();
        }
        User user = User.create(
                request.getUsername(),
                request.getEmail(),
                encoder.encode(request.getPassword())
        );

        Set<String> strRoles = request.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles != null) {
            setRoles(user, strRoles, roles);
            return userRepository.save(user).getId();
        }
        setDefaultRole(user, roles);
        return userRepository.save(user).getId();
    }

    private void setRoles(User user, Set<String> strRoles, Set<Role> roles) {
        strRoles.forEach(role -> {
            if (role.equals("admin")) {
                Role roleAdmin = roleRepository.findByNameOrThrow(ERole.ROLE_ADMIN);
                roles.add(roleAdmin);
            } else {
                Role roleUser = roleRepository.findByNameOrThrow(ERole.ROLE_USER);
                roles.add(roleUser);
            }
        });
        user.setRoles(roles);
    }

    private void setDefaultRole(User user, Set<Role> roles) {
        Role roleUser = roleRepository.findByNameOrThrow(ERole.ROLE_USER);
        roles.add(roleUser);
        user.setRoles(roles);
    }
}
