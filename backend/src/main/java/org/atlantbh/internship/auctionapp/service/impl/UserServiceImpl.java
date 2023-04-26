package org.atlantbh.internship.auctionapp.service.impl;

import org.atlantbh.internship.auctionapp.entity.Role;
import org.atlantbh.internship.auctionapp.entity.UserEntity;
import org.atlantbh.internship.auctionapp.model.User;
import org.atlantbh.internship.auctionapp.repository.UserRepository;
import org.atlantbh.internship.auctionapp.request.LoginRequest;
import org.atlantbh.internship.auctionapp.request.RegisterRequest;
import org.atlantbh.internship.auctionapp.response.AuthResponse;
import org.atlantbh.internship.auctionapp.service.api.UserService;
import org.atlantbh.internship.auctionapp.util.Jwt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    Jwt jwtUtils;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public AuthResponse register(RegisterRequest registerRequest) throws Exception {
        if (userRepository.existsByEmail(registerRequest.getEmail())) {
            throw new Exception("Email already in use");
        }
        UserEntity user = new UserEntity(
                registerRequest.getName(),
                registerRequest.getSurname(),
                registerRequest.getEmail(),
                passwordEncoder.encode(registerRequest.getPassword()),
                Role.USER);
        user = userRepository.save(user);
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(registerRequest.getEmail(), registerRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        user.setPassword(null);
        return new AuthResponse(user.toDomainModel(), jwt);
    }

    public AuthResponse login(LoginRequest loginRequest) throws Exception {
        UserEntity user = userRepository.findByEmail(loginRequest.getEmail()).orElse(null);
        if (user == null || !passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new Exception("Wrong email or password");
        }
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication, loginRequest.getRememberMe());
        user.setPassword(null);
        return new AuthResponse(user.toDomainModel(), jwt);
    }
    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public User getByEmail(String email) {
        return userRepository.findByEmail(email).map(UserEntity::toDomainModel).orElse(null);
    }
}
