package org.atlantbh.internship.auctionapp.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class LoginRequest {

    @NotBlank(message = "Email can't be empty")
    @Email(message = "Email format is not valid")
    private String email;

    @NotBlank(message = "Password can't be empty")
    @Size(min = 8, message = "Password must contain at least 8 characters")
    private String password;

    private boolean rememberMe;

    public LoginRequest() {
    }

    public LoginRequest(@NotBlank(message = "Email can't be empty") @Email(message = "Email format is not valid")
                                String email, @NotBlank(message = "Password can't be empty")
                        @Size(min = 8, message = "Password must contain at least 8 characters") String password,
                        Boolean rememberMe) {
        this.email = email;
        this.password = password;
        this.rememberMe = rememberMe;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getRememberMe() {
        return rememberMe;
    }

    public void setRememberMe(Boolean rememberMe) {
        this.rememberMe = rememberMe;
    }
}
