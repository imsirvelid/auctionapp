package org.atlantbh.internship.auctionapp.service.api;

import org.atlantbh.internship.auctionapp.projection.UserProfileProductsInfo;
import org.atlantbh.internship.auctionapp.response.ProductBidResponse;

import java.util.List;

public interface BidService {
    ProductBidResponse getProductBidInfo(Long productId);
    List<UserProfileProductsInfo> getBidsForUser(Long userId);
}
