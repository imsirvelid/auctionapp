package org.atlantbh.internship.auctionapp.projection;

import java.math.BigDecimal;

public interface RecommendedProduct {
    String getProductName();
    String getProductThumbnail();
    BigDecimal getProductPrice();
}
