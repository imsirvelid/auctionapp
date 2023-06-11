package org.atlantbh.internship.auctionapp.service.impl;

import org.atlantbh.internship.auctionapp.entity.CreditCardEntity;
import org.atlantbh.internship.auctionapp.entity.UserEntity;
import org.atlantbh.internship.auctionapp.exception.BadRequestException;
import org.atlantbh.internship.auctionapp.model.PersonDetails;
import org.atlantbh.internship.auctionapp.repository.CreditCardRepository;
import org.atlantbh.internship.auctionapp.request.CreditCardRequest;
import org.atlantbh.internship.auctionapp.service.api.CreditCardService;
import org.atlantbh.internship.auctionapp.util.Jwt;
import org.springframework.stereotype.Service;

@Service
public class CreditCardServiceImpl implements CreditCardService {

    final private CreditCardRepository creditCardRepository;


    public CreditCardServiceImpl(CreditCardRepository creditCardRepository) {
        this.creditCardRepository = creditCardRepository;
    }

    @Override
    public CreditCardEntity getUserCreditCardInfo(Long userId) {
        return creditCardRepository.findByUserId(userId).orElse(null);
    }

    @Override
    public CreditCardEntity createOrUpdate(CreditCardRequest request) throws BadRequestException {
        PersonDetails user = Jwt.getCurrentUser();
        CreditCardEntity creditCard = new CreditCardEntity(request.getId(), UserEntity.fromPersonDetails(user), request.getNameOnCard(), request.getCardNumber(), request.getExpirationMonth(), request.getExpirationYear(), request.getCvc());
        creditCard = creditCardRepository.save(creditCard);
        return creditCard;
    }
}
