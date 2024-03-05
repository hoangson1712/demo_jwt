package com.demo.jwt.service.impl;

import com.demo.jwt.entity.UserEntity;
import com.demo.jwt.payload.UserRequest;
import com.demo.jwt.repository.UserRepository;
import com.demo.jwt.service.AuthenticationService;
import com.demo.jwt.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public String register(UserRequest userRequest) {
        UserEntity userEntity = new UserEntity();
        userEntity.setFirstName(userRequest.getFirstName());
        userEntity.setLastName(userRequest.getLastName());
        userEntity.setUsername(userRequest.getUsername());
        userEntity.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        userEntity.setRole(userRequest.getRole());
        userRepository.save(userEntity);
        return jwtService.generateToken(userEntity);
    }

    @Override
    public String authenticate(UserRequest userRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userRequest.getUsername(),
                        userRequest.getPassword()
                )
        );
        UserEntity userEntity = userRepository.findByUsername(userRequest.getUsername()).orElseThrow();
        return jwtService.generateToken(userEntity);
    }
}
