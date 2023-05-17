package org.atlantbh.internship.auctionapp.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.atlantbh.internship.auctionapp.projection.UserProfileProductsInfo;
import org.atlantbh.internship.auctionapp.response.ProductBidResponse;
import org.atlantbh.internship.auctionapp.service.api.BidService;
import org.atlantbh.internship.auctionapp.util.Jwt;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Bid", description = "Bid APIs")
@RestController
@RequestMapping("/bids")
public class BidController {

    private final BidService bidService;

    public BidController(final BidService bidService) {
        this.bidService = bidService;
    }

    @GetMapping(value = "/info/{productId}")
    public ResponseEntity<ProductBidResponse> getProductBidInfo(@PathVariable Long productId){
        return ResponseEntity.ok(bidService.getProductBidInfo(productId));
    }

    @GetMapping(value = "/user")
    public ResponseEntity<List<UserProfileProductsInfo>> getBidsForUser(){
        return ResponseEntity.ok(bidService.getBidsForUser(Jwt.getCurrentUserId()));
    }
}
