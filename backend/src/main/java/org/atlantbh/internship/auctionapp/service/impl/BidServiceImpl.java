package org.atlantbh.internship.auctionapp.service.impl;

import org.atlantbh.internship.auctionapp.projection.UserProfileProductsInfo;
import org.atlantbh.internship.auctionapp.repository.BidRepository;
import org.atlantbh.internship.auctionapp.response.ProductBidResponse;
import org.atlantbh.internship.auctionapp.service.api.BidService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class BidServiceImpl implements BidService {

    private final BidRepository bidRepository;

    public BidServiceImpl(final BidRepository bidRepository) {
        this.bidRepository = bidRepository;
    }

    @Override
    public ProductBidResponse getProductBidInfo(Long productId) {
        BigDecimal price = bidRepository.findFirst1ByProductIdOrderByPriceDesc(productId).map(p -> p.getPrice()).orElse(null);
        Integer numberOfBids = bidRepository.countByProductId(productId);
        return new ProductBidResponse(price, numberOfBids);
    }

    @Override
    public List<UserProfileProductsInfo> getBidsForUser(Long userId) {
        for (var dajmi : bidRepository.getUserBids(userId)){
            System.out.println(dajmi.getProductName());
            System.out.println(dajmi.getHighestPrice());
            System.out.println(dajmi.getMyPrice());
            System.out.println(dajmi.getNumberOfBids());
            System.out.println(dajmi.getProductThumbnail());
            System.out.println("*******************");
        }
        System.out.println(bidRepository.getUserBids(userId));
        return bidRepository.getUserBids(userId);
    }
}
