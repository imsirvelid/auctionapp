package org.atlantbh.internship.auctionapp.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.atlantbh.internship.auctionapp.model.PersonDetails;
import org.atlantbh.internship.auctionapp.model.User;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "user")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @NotBlank
    @Column(name = "name")
    private String name;

    @NotBlank
    @Column(name = "surname")
    private String surname;

    @NotBlank
    @Column(name = "email")
    private String email;

    @Column(name = "address")
    private String address;

    @Column(name = "phone")
    private String phone;

    @Column(name = "city")
    private String city;

    @Column(name = "zip_code")
    private Integer zipCode;

    @Column(name = "country")
    private String country;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "password_hash")
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    private String stripeId;

    public UserEntity(String name, String surname,
                      String email,String password, Role role) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public User toDomainModel() {
        return new User(id, name, surname, email, address, phone, city, zipCode, country, imageUrl, password, role, stripeId);
    }

    public static UserEntity fromDomainModel(final User user){
        final UserEntity userEntity = new UserEntity();
        userEntity.setId(user.getId());
        userEntity.setName(user.getName());
        userEntity.setSurname(user.getSurname());
        userEntity.setEmail(user.getEmail());
        userEntity.setAddress(user.getAddress());
        userEntity.setPhone(user.getPhone());
        userEntity.setCity(user.getCity());
        userEntity.setCountry(user.getCountry());
        userEntity.setImageUrl(user.getImageUrl());
        userEntity.setPassword(user.getPassword());
        userEntity.setZipCode(user.getZipCode());
        userEntity.setRole(user.getRole());
        userEntity.setStripeId(user.getStripeId());
        return userEntity;
    }

    public static UserEntity fromPersonDetails(final PersonDetails personDetails) {
        final UserEntity userEntity = new UserEntity();
        userEntity.setId(personDetails.getId());
        userEntity.setRole(personDetails.getRole());
        userEntity.setEmail(personDetails.getEmail());
        userEntity.setPassword(personDetails.getPassword());
        userEntity.setName(personDetails.getName());
        userEntity.setSurname(personDetails.getSurname());
        return userEntity;
    }
}
