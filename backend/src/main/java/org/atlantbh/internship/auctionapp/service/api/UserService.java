package org.atlantbh.internship.auctionapp.service.api;

import org.atlantbh.internship.auctionapp.model.User;
import org.atlantbh.internship.auctionapp.request.LoginRequest;
import org.atlantbh.internship.auctionapp.request.RegisterRequest;
import org.atlantbh.internship.auctionapp.response.AuthResponse;

public interface UserService {
    AuthResponse register(RegisterRequest request) throws Exception;
    AuthResponse login(LoginRequest request) throws Exception;
    boolean existsByEmail(String email);
    User getByEmail(String email);
}
