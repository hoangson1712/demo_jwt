package com.demo.jwt.service;

import com.demo.jwt.payload.UserRequest;

public interface AuthenticationService {
    String register(UserRequest userRequest);
    String authenticate(UserRequest userRequest);
}
