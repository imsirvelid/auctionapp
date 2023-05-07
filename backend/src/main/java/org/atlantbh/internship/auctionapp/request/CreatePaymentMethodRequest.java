package org.atlantbh.internship.auctionapp.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class CreatePaymentMethodRequest {

    @NotNull
    private String number;

    @NotNull
    @Size(min = 5, max = 200)
    private String cvc;

    @NotNull
    private Integer expYear;

    @NotNull
    private Integer expMonth;
}
