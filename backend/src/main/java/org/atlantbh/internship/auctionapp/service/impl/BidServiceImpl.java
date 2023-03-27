package org.atlantbh.internship.auctionapp.service.impl;

import org.atlantbh.internship.auctionapp.repository.BidRepository;
import org.atlantbh.internship.auctionapp.response.ProductBidResponse;
import org.atlantbh.internship.auctionapp.service.api.BidService;
import org.springframework.stereotype.Service;

@Service
public class BidServiceImpl implements BidService {

    private final BidRepository bidRepository;

    public BidServiceImpl(final BidRepository bidRepository) {
        this.bidRepository = bidRepository;
    }

    @Override
    public ProductBidResponse getProductBidInfo(Long productId) {
        var maxBid = bidRepository.findFirst1ByProductIdOrderByPriceDesc(productId);
        var numberOfBids = bidRepository.countByProductId(productId);
        return new ProductBidResponse(maxBid.getPrice(), numberOfBids.intValue());
    }
}
