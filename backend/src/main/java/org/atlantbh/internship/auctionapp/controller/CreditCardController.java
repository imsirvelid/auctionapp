package org.atlantbh.internship.auctionapp.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.atlantbh.internship.auctionapp.entity.CreditCardEntity;
import org.atlantbh.internship.auctionapp.exception.BadRequestException;
import org.atlantbh.internship.auctionapp.request.CreditCardRequest;
import org.atlantbh.internship.auctionapp.service.api.CreditCardService;
import org.atlantbh.internship.auctionapp.util.Jwt;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Credit Card", description = "Credit Card APIs")
@RestController
@RequestMapping("/creditcard")
public class CreditCardController {

    private final CreditCardService creditCardService;

    public CreditCardController(CreditCardService creditCardService) {
        this.creditCardService = creditCardService;
    }

    @GetMapping("/user")
    public ResponseEntity<CreditCardEntity> getUserCreditCardInfo() throws Exception {
        return ResponseEntity.ok(creditCardService.getUserCreditCardInfo(Jwt.getCurrentUserId()));
    }

    @PostMapping("/create")
    public ResponseEntity<CreditCardEntity> createOrUpdate(@RequestBody CreditCardRequest request) throws BadRequestException {
        return ResponseEntity.ok(creditCardService.createOrUpdate(request));
    }

}