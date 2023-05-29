package org.atlantbh.internship.auctionapp.service.impl;

import org.atlantbh.internship.auctionapp.entity.BidEntity;
import org.atlantbh.internship.auctionapp.entity.ProductEntity;
import org.atlantbh.internship.auctionapp.entity.UserEntity;
import org.atlantbh.internship.auctionapp.exception.BadRequestException;
import org.atlantbh.internship.auctionapp.model.PersonDetails;
import org.atlantbh.internship.auctionapp.projection.ProductBidsInfo;
import org.atlantbh.internship.auctionapp.repository.BidRepository;
import org.atlantbh.internship.auctionapp.repository.ProductRepository;
import org.atlantbh.internship.auctionapp.request.BidRequest;
import org.atlantbh.internship.auctionapp.response.ProductBidResponse;
import org.atlantbh.internship.auctionapp.response.ResponseMessage;
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
        Integer numberOfBids = bidRepository.countByProductId(productId);
        ProductBidResponse productBidresponse = bidRepository.findFirst1ByProductIdOrderByPriceDesc(productId).map(bid -> new ProductBidResponse(bid.getPrice(), numberOfBids, bid.getUser().getId())).orElse(new ProductBidResponse(null, 0, null));
        return productBidresponse;
    }

    @Override
    public List<ProductBidsInfo> getBidsForUser(Long userId) {
        return bidRepository.getUserBids(userId);
    }

    @Override
    public BidEntity getMaxBidPriceForProduct(Long productId) throws BadRequestException {
        return bidRepository.findFirst1ByProductIdOrderByPriceDesc(productId)
                .orElseThrow(() -> new BadRequestException("There are no bids for product with given ID"));
    }

    @Override
    public ResponseMessage bid(BidRequest bidRequest, PersonDetails user) throws BadRequestException {
        ProductEntity product = productRepository.findById(bidRequest.getProductId())
                .orElseThrow(() -> new BadRequestException("Product with given id does not exist."));
        if (product.getUser().getId().equals(user.getId()))
            throw new BadRequestException("You can't bid on your own products");
        if (product.getEndDate().isBefore(LocalDateTime.now()))
            return new ResponseMessage(false, "You can no longer bid on this product");
        if (bidRequest.getPrice().compareTo(product.getStartingPrice()) < 0)
            return new ResponseMessage(false, "You can't provide value less than starting price.");
        BigDecimal currentHighest = bidRepository.findFirst1ByProductIdOrderByPriceDesc(bidRequest.getProductId())
                .map(BidEntity::getPrice).orElse(product.getStartingPrice());
        if (bidRequest.getPrice().compareTo(currentHighest) <= 0)
            return new ResponseMessage(false, "There are higher bids than yours. You could give a second try.");
        BidEntity bidEntity = new BidEntity(UserEntity.fromPersonDetails(user), product, bidRequest.getPrice(), LocalDateTime.now(), LocalDateTime.now());
        bidRepository.save(bidEntity);
        return new ResponseMessage(true, "Congrats! You are the highest bidder.");
    }
}
