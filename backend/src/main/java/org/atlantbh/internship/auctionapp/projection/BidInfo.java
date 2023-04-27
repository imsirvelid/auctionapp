package org.atlantbh.internship.auctionapp.projection;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface BidInfo {
    String getProductName();
    String getProductThumbnail();
    Long getProductId();
    LocalDateTime getEndDate();
    BigDecimal getMyPrice();
    BigDecimal getHighestPrice();
    Integer getNumberOfBids();
}
