package org.atlantbh.internship.auctionapp.service.impl;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import com.stripe.model.PaymentMethod;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import org.atlantbh.internship.auctionapp.entity.BidEntity;
import org.atlantbh.internship.auctionapp.entity.CreditCardEntity;
import org.atlantbh.internship.auctionapp.entity.UserEntity;
import org.atlantbh.internship.auctionapp.exception.BadRequestException;
import org.atlantbh.internship.auctionapp.request.CreateCustomerRequest;
import org.atlantbh.internship.auctionapp.service.api.BidService;
import org.atlantbh.internship.auctionapp.service.api.CreditCardService;
import org.atlantbh.internship.auctionapp.service.api.UserService;
import org.atlantbh.internship.auctionapp.util.UserContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class StripeServiceImpl {


    private final CreditCardService creditCardService;
    private final BidService bidService;
    private final UserService userService;

    @Value("${app.stripeSecret}")
    private String STRIPE_API_SECRET;


    public StripeServiceImpl(final CreditCardService creditCardService, BidService bidService, UserService userService) {
        this.creditCardService = creditCardService;
        this.bidService = bidService;
        this.userService = userService;
        Stripe.apiKey = "sk_test_51N3bRHLCipJXkJNqB3dJdUOG7yqoF8Tq90PPL2Mgv3PmLCwrqvPu61PfrJ75sqQQTWR8Lqw6z7r9ATiGMhQ0V1CY00ogdCPbuK";
    }

    public String createPaymentIntent(Long productId) throws StripeException, BadRequestException {
        BidEntity bid = bidService.getMaxBidPriceForProduct(productId);
        UserEntity user = userService.getById(UserContext.getCurrentUserId());
        if (user.getStripeId() == null){
            Customer customer = createCustomer(CreateCustomerRequest.fromUser(user));
            user.setStripeId(customer.getId());
            userService.updateUser(user);
        }
        SessionCreateParams params =
                SessionCreateParams.builder()
                        .setMode(SessionCreateParams.Mode.PAYMENT)
                        .setSuccessUrl("http://localhost:3000/payment/success")
                        .setCancelUrl("http://localhost:3000/payment/cancel")
                        .setCustomer(user.getStripeId())
                        .addLineItem(
                                SessionCreateParams.LineItem.builder()
                                        .setQuantity(1L)
                                        .setPriceData(
                                                SessionCreateParams.LineItem.PriceData.builder()
                                                        .setCurrency("usd")
                                                        .setUnitAmount(bid.getPrice().longValue() * 100L)
                                                        .setProductData(
                                                                SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                                                        .setName(bid.getProduct().getName())
                                                                        .build())
                                                        .build())
                                        .build())
                        .build();
        return Session.create(params).getUrl();
    }

    public Customer createCustomer(CreateCustomerRequest request) throws StripeException {
        Map<String, Object> params = new HashMap<>();
        CreditCardEntity creditCard = creditCardService.getUserCreditCardInfo(UserContext.getCurrentUserId());
        if (creditCard != null)
            params.put("payment_method", createPaymentMethod(creditCard).getId());
        params.put("name", request.getName());
        params.put("email", request.getEmail());
        return Customer.create(params);
    }

    public PaymentMethod createPaymentMethod(CreditCardEntity request) throws StripeException {
        Map<String, Object> card = new HashMap<>();
        card.put("number", request.getCardNumber());
        card.put("exp_month", request.getExpirationMonth());
        card.put("exp_year", request.getExpirationYear());
        card.put("cvc", request.getCvc());
        Map<String, Object> params = new HashMap<>();
        params.put("type", "card");
        params.put("card", card);
        return PaymentMethod.create(params);
    }

}
