package org.atlantbh.internship.auctionapp.service.api;

import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import com.stripe.model.PaymentMethod;
import org.atlantbh.internship.auctionapp.entity.CreditCardEntity;
import org.atlantbh.internship.auctionapp.exception.BadRequestException;
import org.atlantbh.internship.auctionapp.request.CreateCustomerRequest;

import java.util.List;

public interface PaymentService {

    String createPaymentIntent(Long productId) throws StripeException, BadRequestException;
    Customer createCustomer(CreateCustomerRequest request) throws StripeException;
    PaymentMethod createPaymentMethod(CreditCardEntity request) throws StripeException;
    List<PaymentMethod> getUserPaymentMethod(Long userId) throws StripeException, BadRequestException;
    String getUserCustomerId(Long userId) throws BadRequestException;
}
