package org.atlantbh.internship.auctionapp.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.atlantbh.internship.auctionapp.model.PersonDetails;
import org.atlantbh.internship.auctionapp.response.ProductBidResponse;
import org.atlantbh.internship.auctionapp.service.api.BidService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        System.out.println(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        PersonDetails personDetails = (PersonDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        bidService.getBidsForUser(personDetails.getId());
        return ResponseEntity.ok(bidService.getBidsForUser(personDetails.getId()));
    }
}
