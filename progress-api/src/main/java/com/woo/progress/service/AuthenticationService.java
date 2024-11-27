package com.woo.progress.service;


import com.woo.progress.controller.payload.request.AuthenticationRequest;
import com.woo.progress.controller.payload.request.RegisterRequest;
import com.woo.progress.controller.payload.response.AuthenticationResponse;

public interface AuthenticationService {
    AuthenticationResponse register(RegisterRequest request);

    AuthenticationResponse authenticate(AuthenticationRequest request);
}
