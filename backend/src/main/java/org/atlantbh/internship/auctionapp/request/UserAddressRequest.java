package org.atlantbh.internship.auctionapp.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserAddressRequest {

    @NotBlank(message = "Address can't be empty")
    @Size(min = 2, message = "Address must have at least 2 characters")
    @Size(max = 50, message = "Address can't be longer than 50 characters")
    private String address;

    @NotBlank(message = "City can't be empty")
    private String city;

    @NotBlank(message = "Country can't be empty")
    private String country;

    @NotBlank(message = "Zip code can't be empty")
    private Integer zipCode;

    @NotBlank(message = "Phone can't be empty")
    private String phone;
}
