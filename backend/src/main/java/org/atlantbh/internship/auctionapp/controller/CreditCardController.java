package org.atlantbh.internship.auctionapp.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.atlantbh.internship.auctionapp.entity.CreditCardEntity;
import org.atlantbh.internship.auctionapp.exception.BadRequestException;
import org.atlantbh.internship.auctionapp.model.PersonDetails;
import org.atlantbh.internship.auctionapp.request.CreditCardRequest;
import org.atlantbh.internship.auctionapp.service.api.CreditCardService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Credit Card", description = "Credit Card APIs")
@RestController
@RequestMapping("/creditcard")
public class CreditCardController {

    private final CreditCardService creditCardService;

    public CreditCardController(CreditCardService creditCardService) {
        this.creditCardService = creditCardService;
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/user")
    public ResponseEntity<CreditCardEntity> getUserCreditCardInfo() throws Exception {
        PersonDetails personDetails = (PersonDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok(creditCardService.getUserCreditCardInfo(personDetails.getId()));
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/create")
    public ResponseEntity<CreditCardEntity> createOrUpdate(@RequestBody CreditCardRequest request) throws BadRequestException {
        return ResponseEntity.ok(creditCardService.createOrUpdate(request));
    }

}