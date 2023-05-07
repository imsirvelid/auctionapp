package org.atlantbh.internship.auctionapp.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class CreatePaymentRequest {

    @NotNull
    @Min(4)
    private Long amount;

    @NotNull
    @Size(min = 5, max = 200)
    private String name;
}
