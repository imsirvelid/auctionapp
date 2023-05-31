package org.atlantbh.internship.auctionapp.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.atlantbh.internship.auctionapp.exception.BadRequestException;
import org.atlantbh.internship.auctionapp.model.User;
import org.atlantbh.internship.auctionapp.request.UserContactInfoRequest;
import org.atlantbh.internship.auctionapp.service.api.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "User", description = "User APIs")
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PreAuthorize("hasRole('USER')")
    @PutMapping("/address")
    public ResponseEntity<User> updateContactInfo(@RequestBody UserContactInfoRequest request) throws BadRequestException {
        return ResponseEntity.ok(userService.updateAddress(request));
    }
}
