package org.atlantbh.internship.auctionapp.request;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CreditCardRequest {

    @Nullable
    private Long id;

    @NotBlank(message = "Name on card can't be empty")
    @Size(min = 2, message = "Name on card must have at least 2 characters")
    @Size(max = 50, message = "Name on card can't be longer than 50 characters")
    private String nameOnCard;

    @NotBlank(message = "Card number can't be empty")
    private String cardNumber;

    @NotBlank(message = "Expiration month can't be empty")
    private Integer expirationMonth;

    @NotBlank(message = "Expiration year can't be empty")
    private Integer expirationYear;

    @NotBlank(message = "Cvc can't be empty")
    private String cvc;
}
