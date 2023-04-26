package org.atlantbh.internship.auctionapp.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class RegisterRequest {

    @NotBlank(message = "First name can't be empty")
    @Size(min = 2, message = "First name must have at least 2 characters")
    @Size(max = 50, message = "First name can't be longer than 50 characters")
    private String name;

    @NotBlank(message = "Last name can't be empty")
    @Size(min = 2, message = "First name must have at least 2 characters")
    @Size(max = 50, message = "First name can't be longer than 50 characters")
    private String surname;

    @NotBlank(message = "Email can't be empty")
    @Email(message = "Email format is not valid")
    @Size(max = 320, message = "Email can't be longer than 320 characters")
    private String email;

    @NotBlank(message = "Password can't be empty")
    @Size(min = 8, message = "Password must contain at least 8 characters")
    @Size(max = 255, message = "Password can't be longer than 255 characters")
    private String password;

    public RegisterRequest() {
    }

    public RegisterRequest(@NotBlank(message = "First name can't be empty") String name,
                           @NotBlank(message = "Last name can't be empty") String lastName,
                           @NotBlank(message = "Email can't be empty")
                           @Email(message = "Email format is not valid") String email,
                           @NotBlank(message = "Password can't be empty")
                           @Size(min = 8, message = "Password must contain at least 8 characters") String password) {
        this.name = name;
        this.surname = lastName;
        this.email = email;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
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
}
