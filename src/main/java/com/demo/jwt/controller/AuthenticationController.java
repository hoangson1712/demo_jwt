package com.demo.jwt.controller;

import com.demo.jwt.payload.UserRequest;
import com.demo.jwt.service.impl.AuthenticationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class AuthenticationController {
    @Autowired
    private AuthenticationServiceImpl authenticationServiceImpl;
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserRequest userRequest){
        return ResponseEntity.ok(authenticationServiceImpl.register(userRequest));
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserRequest userRequest){
        return ResponseEntity.ok(authenticationServiceImpl.authenticate(userRequest));
    }
}
