package org.atlantbh.internship.auctionapp.model;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import org.atlantbh.internship.auctionapp.entity.Status;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Data
public class User {
    private Long id;
    private String name;
    private String surname;
    private String email;
    private String address;
    private String phone;
    private String city;
    private String country;
    private String imageUrl;
}
