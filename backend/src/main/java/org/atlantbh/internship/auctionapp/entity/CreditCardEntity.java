package org.atlantbh.internship.auctionapp.entity;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@ToString
@Table(name = "credit_card")
public class CreditCardEntity {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @JoinColumn(name = "name_on_card")
    private String nameOnCard;

    @Column(name = "card_number")
    private String cardNumber;

    @Column(name = "expiration_month")
    private Integer expirationMonth;

    @Column(name = "expiration_year")
    private Integer expirationYear;

    @Column(name = "cvc")
    private String cvc;

    public CreditCardEntity(UserEntity user, String nameOnCard, String cardNumber, Integer expirationMonth, Integer expirationYear, String cvc) {
        this.user = user;
        this.nameOnCard = nameOnCard;
        this.cardNumber = cardNumber;
        this.expirationMonth = expirationMonth;
        this.expirationYear = expirationYear;
        this.cvc = cvc;
    }
}
