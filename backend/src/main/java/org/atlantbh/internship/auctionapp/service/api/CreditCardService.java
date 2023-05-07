package org.atlantbh.internship.auctionapp.service.api;

import org.atlantbh.internship.auctionapp.entity.CreditCardEntity;
import org.atlantbh.internship.auctionapp.exception.BadRequestException;
import org.atlantbh.internship.auctionapp.request.CreditCardRequest;

public interface CreditCardService {
    CreditCardEntity getUserCreditCardInfo(Long userId);
    CreditCardEntity createOrUpdate(CreditCardRequest request) throws BadRequestException;
}
