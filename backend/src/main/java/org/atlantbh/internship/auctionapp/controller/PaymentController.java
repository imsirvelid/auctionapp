package org.atlantbh.internship.auctionapp.controller;

import com.stripe.exception.StripeException;
import org.atlantbh.internship.auctionapp.exception.BadRequestException;
import org.atlantbh.internship.auctionapp.service.impl.StripeServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    final private StripeServiceImpl stripeService;

    public PaymentController(StripeServiceImpl stripeService) {
        this.stripeService = stripeService;
    }

    @PostMapping("/create-payment-intent")
    public ResponseEntity<?> createPaymentIntent(@RequestBody Long productId) throws StripeException, BadRequestException {
        return ResponseEntity.ok(stripeService.createPaymentIntent(productId));
    }
}