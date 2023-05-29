package org.atlantbh.internship.auctionapp.service.api;

import org.atlantbh.internship.auctionapp.entity.BidEntity;
import org.atlantbh.internship.auctionapp.exception.BadRequestException;
import org.atlantbh.internship.auctionapp.model.PersonDetails;
import org.atlantbh.internship.auctionapp.projection.ProductBidsInfo;
import org.atlantbh.internship.auctionapp.request.BidRequest;
import org.atlantbh.internship.auctionapp.response.ProductBidResponse;
import org.atlantbh.internship.auctionapp.response.SuccessfulAndMessage;

import java.util.List;
public interface BidService {
    ProductBidResponse getProductBidInfo(Long productId);
    List<ProductBidsInfo> getBidsForUser(Long userId);
    SuccessfulAndMessage bid(BidRequest bidRequest, PersonDetails user) throws BadRequestException;
    BidEntity getMaxBidPriceForProduct(Long productId) throws BadRequestException;
}
