package org.atlantbh.internship.auctionapp.controller;

import com.stripe.exception.StripeException;
import com.stripe.model.PaymentMethod;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.atlantbh.internship.auctionapp.exception.BadRequestException;
import org.atlantbh.internship.auctionapp.service.api.PaymentService;
import org.atlantbh.internship.auctionapp.util.Jwt;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Payment", description = "Payment APIs")
@RestController
@RequestMapping("/payment")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/create-payment-intent")
    public ResponseEntity<String> createPaymentIntent(@RequestBody Long productId) throws StripeException, BadRequestException {
        return ResponseEntity.ok(paymentService.createPaymentIntent(productId));
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/user/payment-methods")
    public ResponseEntity<List<PaymentMethod>> getUserPaymentMethods() throws StripeException, BadRequestException {
        return ResponseEntity.ok(paymentService.getUserPaymentMethod(Jwt.getCurrentUserId()));
    }
}