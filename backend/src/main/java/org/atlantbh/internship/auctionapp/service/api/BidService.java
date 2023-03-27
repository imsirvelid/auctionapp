package org.atlantbh.internship.auctionapp.service.api;

import org.atlantbh.internship.auctionapp.response.ProductBidResponse;

public interface BidService {
    ProductBidResponse getProductBidInfo(Long productId);
}
