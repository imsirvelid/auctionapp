package org.atlantbh.internship.auctionapp.controller;

import com.stripe.exception.StripeException;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.atlantbh.internship.auctionapp.exception.BadRequestException;
import org.atlantbh.internship.auctionapp.service.api.StripeService;
import org.atlantbh.internship.auctionapp.service.impl.StripeServiceImpl;
import org.atlantbh.internship.auctionapp.util.Jwt;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Payment", description = "Payment APIs")
@RestController
@RequestMapping("/payment")
public class PaymentController {

    final private StripeService stripeService;

    public PaymentController(StripeServiceImpl stripeService) {
        this.stripeService = stripeService;
    }

    @PostMapping("/create-payment-intent")
    public ResponseEntity<?> createPaymentIntent(@RequestBody Long productId) throws StripeException, BadRequestException {
        return ResponseEntity.ok(stripeService.createPaymentIntent(productId));
    }

    @GetMapping("/user/payment-methods")
    public ResponseEntity<?> getUserPaymentMethods() throws StripeException, BadRequestException {
        return ResponseEntity.ok(stripeService.getUserPaymentMethod(Jwt.getCurrentUserId()));
    }
}