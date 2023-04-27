package org.atlantbh.internship.auctionapp.service.impl;

import org.atlantbh.internship.auctionapp.entity.BidEntity;
import org.atlantbh.internship.auctionapp.entity.ProductEntity;
import org.atlantbh.internship.auctionapp.entity.UserEntity;
import org.atlantbh.internship.auctionapp.exception.BadRequestException;
import org.atlantbh.internship.auctionapp.model.PersonDetails;
import org.atlantbh.internship.auctionapp.projection.UserProfileProductsInfo;
import org.atlantbh.internship.auctionapp.repository.BidRepository;
import org.atlantbh.internship.auctionapp.repository.ProductRepository;
import org.atlantbh.internship.auctionapp.request.BidRequest;
import org.atlantbh.internship.auctionapp.response.BooleanString;
import org.atlantbh.internship.auctionapp.response.ProductBidResponse;
import org.atlantbh.internship.auctionapp.service.api.BidService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class BidServiceImpl implements BidService {

    private final BidRepository bidRepository;
    private final ProductRepository productRepository;

    public BidServiceImpl(final BidRepository bidRepository, final ProductRepository productRepository) {
        this.bidRepository = bidRepository;
        this.productRepository = productRepository;
    }

    @Override
    public ProductBidResponse getProductBidInfo(Long productId) {
        BigDecimal price = bidRepository.findFirst1ByProductIdOrderByPriceDesc(productId).map(p -> p.getPrice()).orElse(null);
        Integer numberOfBids = bidRepository.countByProductId(productId);
        return new ProductBidResponse(price, numberOfBids);
    }

    @Override
    public List<UserProfileProductsInfo> getBidsForUser(Long userId) {
        return bidRepository.getUserBids(userId);
    }

    @Override
    public BooleanString bid(BidRequest bidRequest, PersonDetails user) throws BadRequestException {
        ProductEntity product = productRepository.findById(bidRequest.getProductId())
                .orElseThrow(() -> new BadRequestException("Product with given id does not exist."));
        if (product.getUser().getId().equals(user.getId()))
            throw new BadRequestException("You can't bid to your own products");
        BigDecimal currentHighest = bidRepository.findFirst1ByProductIdOrderByPriceDesc(bidRequest.getProductId())
                .map(BidEntity::getPrice).orElse(product.getStartingPrice());
        if (product.getEndDate().isBefore(LocalDateTime.now()))
            return new BooleanString(false, "You can no longer bid on this product");
        if (bidRequest.getPrice().compareTo(product.getStartingPrice()) <= 0)
            return new BooleanString(false, "You have to provide value grater than starting price.");
        if (bidRequest.getPrice().compareTo(currentHighest) <= 0)
            return new BooleanString(false, "There are higher bids than yours. You could give a second try.");
        BidEntity bidEntity = new BidEntity(UserEntity.fromPersonDetails(user), product, bidRequest.getPrice(), LocalDateTime.now(), LocalDateTime.now());
        bidRepository.save(bidEntity);
        return new BooleanString(true, "Congrats! You are the highest bidder.");
    }
}
