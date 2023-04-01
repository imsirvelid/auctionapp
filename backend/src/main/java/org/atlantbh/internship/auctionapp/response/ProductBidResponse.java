package org.atlantbh.internship.auctionapp.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
@Setter
public class ProductBidResponse {
    private BigDecimal highestBid;
    private int numberOfBids;
}
