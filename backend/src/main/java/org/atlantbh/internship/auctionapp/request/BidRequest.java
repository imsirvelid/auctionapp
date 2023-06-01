package org.atlantbh.internship.auctionapp.request;

import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class BidRequest {
    private Long productId;
    private BigDecimal price;
}
