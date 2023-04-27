package org.atlantbh.internship.auctionapp.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.atlantbh.internship.auctionapp.exception.BadRequestException;
import org.atlantbh.internship.auctionapp.model.PersonDetails;
import org.atlantbh.internship.auctionapp.request.BidRequest;
import org.atlantbh.internship.auctionapp.response.ProductBidResponse;
import org.atlantbh.internship.auctionapp.service.api.BidService;
import org.atlantbh.internship.auctionapp.util.Jwt;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<?> getBidsForUser(){
        return ResponseEntity.ok(bidService.getBidsForUser(Jwt.getCurrentUserId()));
    }

    @PostMapping(value = "/bid")
    public ResponseEntity<?> bid(@RequestBody BidRequest bidRequest) throws BadRequestException {
        return ResponseEntity.ok(bidService.bid(bidRequest, (PersonDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()));
    }
}
