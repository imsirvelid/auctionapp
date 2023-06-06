package org.atlantbh.internship.auctionapp.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.atlantbh.internship.auctionapp.exception.BadRequestException;
import org.atlantbh.internship.auctionapp.projection.ProductBidsInfo;
import org.atlantbh.internship.auctionapp.request.BidRequest;
import org.atlantbh.internship.auctionapp.response.ProductBidResponse;
import org.atlantbh.internship.auctionapp.response.ResponseMessage;
import org.atlantbh.internship.auctionapp.service.api.BidService;
import org.atlantbh.internship.auctionapp.util.Jwt;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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

    @PreAuthorize("hasRole('USER')")
    @GetMapping(value = "/user")
    public ResponseEntity<List<ProductBidsInfo>> getBidsForUser(){
        return ResponseEntity.ok(bidService.getBidsForUser(Jwt.getCurrentUserId()));
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping(value = "/bid")
    public ResponseEntity<ResponseMessage> bid(@RequestBody BidRequest bidRequest) throws BadRequestException {
        return ResponseEntity.ok(bidService.bid(bidRequest, Jwt.getCurrentUser()));
    }
}
