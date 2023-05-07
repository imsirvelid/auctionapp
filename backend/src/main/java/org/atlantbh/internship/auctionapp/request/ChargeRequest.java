package org.atlantbh.internship.auctionapp.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChargeRequest {
    private String description;
    private BigDecimal amount;
    private String currency;
    private String stripeEmail;
    private String stripeToken;
}
