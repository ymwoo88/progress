package com.woo.progress.service.impl;

import com.woo.progress.repository.entities.User;
import com.woo.progress.enums.TokenType;
import com.woo.progress.controller.payload.request.AuthenticationRequest;
import com.woo.progress.controller.payload.request.RegisterRequest;
import com.woo.progress.controller.payload.response.AuthenticationResponse;
import com.woo.progress.repository.UserRepository;
import com.woo.progress.service.AuthenticationService;
import com.woo.progress.service.JwtService;
import com.woo.progress.service.RefreshTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final RefreshTokenService refreshTokenService;

    @Override
    public AuthenticationResponse register(RegisterRequest request) {
        var user = User.builder()
                .enName(request.getEnName())
                .userName(request.getUserName())
                .userId(request.getUserId())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .build();
        user = userRepository.save(user);
        var jwt = jwtService.generateToken(user);
        var refreshToken = refreshTokenService.createRefreshToken(user.getId());

        var roles = user.getRole().getAuthorities()
                .stream()
                .map(SimpleGrantedAuthority::getAuthority)
                .toList();

        return AuthenticationResponse.builder()
                .accessToken(jwt)
                .loginId(user.getUserId())
                .id(user.getId())
                .refreshToken(refreshToken.getToken())
                .roles(roles)
                .tokenType(TokenType.BEARER.name())
                .build();
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUserId(), request.getPassword()));

        var user = userRepository.findByUserId(request.getUserId()).orElseThrow(() -> new IllegalArgumentException("Invalid email or password."));
        var roles = user.getRole().getAuthorities()
                .stream()
                .map(SimpleGrantedAuthority::getAuthority)
                .toList();
        var jwt = jwtService.generateToken(user);
        var refreshToken = refreshTokenService.createRefreshToken(user.getId());
        return AuthenticationResponse.builder()
                .accessToken(jwt)
                .roles(roles)
                .loginId(user.getUserId())
                .id(user.getId())
                .refreshToken(refreshToken.getToken())
                .tokenType(TokenType.BEARER.name())
                .build();
    }
}
