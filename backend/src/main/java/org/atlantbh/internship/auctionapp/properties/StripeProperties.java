package org.atlantbh.internship.auctionapp.properties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StripeProperties {
    private String stripeSecret;

    public String getStripeSecret() {
        return stripeSecret;
    }

    public void setStripeSecret(String stripeSecret) {
        this.stripeSecret = stripeSecret;
    }
}