package org.atlantbh.internship.auctionapp.service.api;

import org.atlantbh.internship.auctionapp.exception.BadRequestException;
import org.atlantbh.internship.auctionapp.exception.PaymentException;

public interface PaymentService {

    String createPaymentIntent(Long productId) throws PaymentException, BadRequestException;
}
