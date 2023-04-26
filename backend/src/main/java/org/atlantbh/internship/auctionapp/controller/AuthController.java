package org.atlantbh.internship.auctionapp.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.atlantbh.internship.auctionapp.exception.BadRequestException;
import org.atlantbh.internship.auctionapp.request.LoginRequest;
import org.atlantbh.internship.auctionapp.request.RegisterRequest;
import org.atlantbh.internship.auctionapp.response.AuthResponse;
import org.atlantbh.internship.auctionapp.service.api.UserService;
import org.atlantbh.internship.auctionapp.util.Jwt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Auth", description = "Authentication and Authorization APIs")
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    @Autowired
    private Jwt jwtUtils;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity logIn(@Valid @RequestBody LoginRequest loginRequest) throws Exception {
        AuthResponse authResponse = userService.login(loginRequest);
        System.out.println("After login authentication is: " + SecurityContextHolder.getContext().getAuthentication());
        return ResponseEntity.ok(authResponse);
    }

    @PostMapping("/register")
    public ResponseEntity createAccount(@Valid @RequestBody RegisterRequest signUpRequest) throws Exception {
        AuthResponse authResponse = userService.register(signUpRequest);
        return ResponseEntity.ok(authResponse);
    }

    @PostMapping("/logout")
    public void fakeLogout() {
        throw new IllegalStateException("This method shouldn't be called. It's implemented by Spring Security filters.");
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/getuser")
    public ResponseEntity<?> validateToken(HttpServletRequest request) throws BadRequestException {
        System.out.println(request.getHeader("Authorization"));
        System.out.println(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return ResponseEntity.ok(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/test")
    public ResponseEntity<String> testMethod(HttpServletRequest request){
        System.out.println(SecurityContextHolder.getContext().getAuthentication());
        System.out.println(request.getHeader("Authorization"));
        return ResponseEntity.ok("This is just a test");
    }
}