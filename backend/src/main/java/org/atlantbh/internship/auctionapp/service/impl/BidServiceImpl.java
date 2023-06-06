package org.atlantbh.internship.auctionapp.service.impl;

import org.atlantbh.internship.auctionapp.entity.*;
import org.atlantbh.internship.auctionapp.exception.BadRequestException;
import org.atlantbh.internship.auctionapp.model.PersonDetails;
import org.atlantbh.internship.auctionapp.projection.ProductBidsInfo;
import org.atlantbh.internship.auctionapp.repository.BidRepository;
import org.atlantbh.internship.auctionapp.repository.ProductRepository;
import org.atlantbh.internship.auctionapp.request.BidRequest;
import org.atlantbh.internship.auctionapp.response.ProductBidResponse;
import org.atlantbh.internship.auctionapp.response.ResponseMessage;
import org.atlantbh.internship.auctionapp.service.api.BidService;
import org.atlantbh.internship.auctionapp.service.api.NotificationService;
import org.atlantbh.internship.auctionapp.util.Jwt;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class BidServiceImpl implements BidService {

    private final BidRepository bidRepository;
    private final ProductRepository productRepository;

    private final NotificationService notificationService;

    public BidServiceImpl(final BidRepository bidRepository, final ProductRepository productRepository, NotificationService notificationService) {
        this.bidRepository = bidRepository;
        this.productRepository = productRepository;
        this.notificationService = notificationService;
    }

    @Override
    public ProductBidResponse getProductBidInfo(Long productId) {
        BigDecimal price = bidRepository.findFirst1ByProductIdOrderByPriceDesc(productId).map(p -> p.getPrice()).orElse(null);
        Integer numberOfBids = bidRepository.countByProductId(productId);
        return new ProductBidResponse(price, numberOfBids);
    }

    @Override
    public List<ProductBidsInfo> getBidsForUser(Long userId) {
        return bidRepository.getUserBids(userId);
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
        BidEntity highestBid = bidRepository.findFirst1ByProductIdOrderByPriceDesc(bidRequest.getProductId())
                .orElse(null);
        BigDecimal currentHighest = product.getStartingPrice();
        if (highestBid != null)
            currentHighest = highestBid.getPrice();
        if (highestBid != null && bidRequest.getPrice().compareTo(currentHighest) <= 0)
            return new ResponseMessage(false, "There are higher bids than yours. You could give a second try.");
        BidEntity bidEntity = new BidEntity(UserEntity.fromPersonDetails(user), product, bidRequest.getPrice(), LocalDateTime.now(), LocalDateTime.now());
        if (highestBid != null){
            NotificationEntity notification = new NotificationEntity(UserEntity.fromPersonDetails(Jwt.getCurrentUser()), product, NotificationType.INFO,
                    "You are outbided on product " + product.getName() + "($" + bidRequest.getPrice() + ")", LocalDateTime.now(), false);
            notificationService.sendNotification(notification);
        }
        bidRepository.save(bidEntity);

        return new ResponseMessage(true, "Congrats! You are the highest bidder.");
    }
}
