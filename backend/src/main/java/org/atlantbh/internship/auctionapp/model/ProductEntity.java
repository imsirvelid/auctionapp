package org.atlantbh.internship.auctionapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@ToString
@Table(name = "product")
public class ProductEntity {

    @Id
    @GeneratedValue
    private Long id;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @Column(name = "name")
    private String name;

    @Column(name = "details")
    private String details;

    @Column(name = "starting_price")
    private Double startingPrice;

    @Column(name = "created")
    private LocalDateTime created;

    @Column(name = "start_date")
    private LocalDateTime startDate;

    @Column(name = "end_date")
    private LocalDateTime endDate;

    @Column(name = "thumbnail_url")
    private String thumbnailUrl;

    @Column(name = "status")
    private STATUS status;
}
