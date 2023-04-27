package org.atlantbh.internship.auctionapp.service.api;

import org.atlantbh.internship.auctionapp.projection.BidInfo;
import org.atlantbh.internship.auctionapp.response.ProductBidResponse;

import java.util.List;

public interface BidService {
    ProductBidResponse getProductBidInfo(Long productId);
    List<BidInfo> getBidsForUser(Long userId);
}
