package org.atlantbh.internship.auctionapp.config;

import lombok.Getter;
import lombok.Setter;
import org.atlantbh.internship.auctionapp.properties.FirebaseProperties;
import org.atlantbh.internship.auctionapp.properties.StripeProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "external")
@Getter
@Setter
public class ExternalProperties {
    private FirebaseProperties firebase;
    private StripeProperties stripe;
}