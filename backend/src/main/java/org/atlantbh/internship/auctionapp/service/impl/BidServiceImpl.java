package org.atlantbh.internship.auctionapp.service.impl;

import org.atlantbh.internship.auctionapp.entity.*;
import org.atlantbh.internship.auctionapp.exception.BadRequestException;
import org.atlantbh.internship.auctionapp.model.PersonDetails;
import org.atlantbh.internship.auctionapp.projection.ProductBidsInfo;
import org.atlantbh.internship.auctionapp.repository.BidRepository;
import org.atlantbh.internship.auctionapp.repository.ProductRepository;
import org.atlantbh.internship.auctionapp.request.BidRequest;
import org.atlantbh.internship.auctionapp.response.FailedResponseMessage;
import org.atlantbh.internship.auctionapp.response.ProductBidResponse;
import org.atlantbh.internship.auctionapp.response.ResponseMessage;
import org.atlantbh.internship.auctionapp.response.SuccessResponseMessage;
import org.atlantbh.internship.auctionapp.service.api.BidService;
import org.atlantbh.internship.auctionapp.service.api.NotificationService;
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
            return new FailedResponseMessage("You can no longer bid on this product");
        if (bidRequest.getPrice().compareTo(product.getStartingPrice()) < 0)
            return new FailedResponseMessage("You can't provide value less than starting price.");
        BidEntity highestBid = bidRepository.findFirst1ByProductIdOrderByPriceDesc(bidRequest.getProductId())
                .orElse(null);
        BigDecimal currentHighest = product.getStartingPrice();
        if (highestBid != null)
            currentHighest = highestBid.getPrice();
        if (highestBid != null && bidRequest.getPrice().compareTo(currentHighest) <= 0)
            return new FailedResponseMessage("There are higher bids than yours. You could give a second try.");
        BidEntity bidEntity = new BidEntity(UserEntity.fromPersonDetails(user), product, bidRequest.getPrice(), LocalDateTime.now(), LocalDateTime.now());
        if (highestBid != null){
            NotificationEntity notification = new NotificationEntity(highestBid.getUser(), product, NotificationType.INFO,
                    "You are outbided on product " + product.getName() + "($" + bidRequest.getPrice() + ")", LocalDateTime.now(), false);
            notificationService.sendNotification(notification);
        }
        bidRepository.save(bidEntity);
        return new SuccessResponseMessage("Congrats! You are the highest bidder.");
    }
}
