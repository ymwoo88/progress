package com.woo.progress.config.security.service;


import com.woo.progress.config.security.payload.request.AuthenticationRequest;
import com.woo.progress.config.security.payload.request.RegisterRequest;
import com.woo.progress.config.security.payload.response.AuthenticationResponse;

public interface AuthenticationService {
    AuthenticationResponse register(RegisterRequest request);

    AuthenticationResponse authenticate(AuthenticationRequest request);
}
