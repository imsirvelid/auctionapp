package org.atlantbh.internship.auctionapp.service.impl;

import org.atlantbh.internship.auctionapp.entity.CreditCardEntity;
import org.atlantbh.internship.auctionapp.entity.UserEntity;
import org.atlantbh.internship.auctionapp.exception.BadRequestException;
import org.atlantbh.internship.auctionapp.model.PersonDetails;
import org.atlantbh.internship.auctionapp.repository.CreditCardRepository;
import org.atlantbh.internship.auctionapp.request.CreditCardRequest;
import org.atlantbh.internship.auctionapp.service.api.CreditCardService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class CreditCardServiceImpl implements CreditCardService {

    private final CreditCardRepository creditCardRepository;


    public CreditCardServiceImpl(CreditCardRepository creditCardRepository) {
        this.creditCardRepository = creditCardRepository;
    }

    @Override
    public CreditCardEntity getUserCreditCardInfo(Long userId) {
        return creditCardRepository.findByUserId(userId).orElse(null);
    }

    @Override
    public CreditCardEntity createOrUpdate(CreditCardRequest request) throws BadRequestException {
        PersonDetails user = (PersonDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        CreditCardEntity creditCard = new CreditCardEntity(request.getId(), UserEntity.fromPersonDetails(user), request.getNameOnCard(), request.getCardNumber(), request.getExpirationMonth(), request.getExpirationYear(), request.getCvc());
        creditCard = creditCardRepository.save(creditCard);
        return creditCard;
    }
}
