package com.bside.starterapi.api.application.auth;

import com.bside.starterapi.api.config.auth.JwtUtils;
import com.bside.starterapi.api.config.auth.UserDetailsImpl;
import com.bside.starterapi.api.domain.auth.RefreshToken;
import com.bside.starterapi.api.domain.post.PostRepository;
import com.bside.starterapi.api.domain.post.book.Book;
import com.bside.starterapi.api.domain.post.display.Display;
import com.bside.starterapi.api.domain.post.media.Media;
import com.bside.starterapi.api.domain.user.*;
import com.bside.starterapi.api.presentation.auth.dto.AuthenticateUserRequest;
import com.bside.starterapi.api.presentation.auth.dto.JwtResponse;
import com.bside.starterapi.api.presentation.auth.dto.RegisterUserRequest;
import com.bside.starterapi.api.presentation.post.dto.PostRegisterRequest;
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

    private final PostRepository postRepository;

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
                encoder.encode(request.getPassword()),
                request.getNickname()
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

    @Transactional
    public Long registerBookPost(Long userId, PostRegisterRequest request) {
        Book book = Book.builder()
                .user(userRepository.getById(userId))
                .bookTitle(request.getTopic())
                .title(request.getPostTitle())
                .content(request.getPostContent())
                .isShare(request.isShare())
                .star(request.getStar())
                .imageUrl(request.getImageUrl())
                .build();
        return postRepository.save(book).getId();
    }

    @Transactional
    public Long registerMediaPost(Long userId, PostRegisterRequest request) {
        Media media = Media.builder()
                .user(userRepository.getById(userId))
                .mediaTitle(request.getTopic())
                .title(request.getPostTitle())
                .content(request.getPostContent())
                .isShare(request.isShare())
                .star(request.getStar())
                .imageUrl(request.getImageUrl())
                .build();
        return postRepository.save(media).getId();
    }

    @Transactional
    public Long registerDisplayPost(Long userId, PostRegisterRequest request) {
        Display display = Display.builder()
                .user(userRepository.getById(userId))
                .displayTitle(request.getTopic())
                .location(request.getLocation())
                .title(request.getPostTitle())
                .content(request.getPostContent())
                .isShare(request.isShare())
                .imageUrl(request.getImageUrl())
                .star(request.getStar())
                .build();
        return postRepository.save(display).getId();
    }
}
