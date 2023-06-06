package org.atlantbh.internship.auctionapp.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.atlantbh.internship.auctionapp.entity.UserEntity;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class CreateCustomerRequest {

    @NotNull
    @Email
    private String email;

    @NotNull
    @Size(min = 5, max = 200)
    private String name;

    public static CreateCustomerRequest fromUser(UserEntity entity) {
        return new CreateCustomerRequest(entity.getEmail(), entity.getName());
    }
}
