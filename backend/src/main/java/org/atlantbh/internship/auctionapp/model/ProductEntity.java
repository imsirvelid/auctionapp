package org.atlantbh.internship.auctionapp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

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

    @NotBlank
    @Column(name = "name")
    private String name;

    @NotBlank
    @Column(name = "details")
    private String details;

    @NotBlank
    @Column(name = "starting_price")
    private Double startingPrice;

    @NotBlank
    @Column(name = "created")
    private LocalDateTime created;

    @NotBlank
    @Column(name = "start_date")
    private LocalDateTime startDate;

    @NotBlank
    @Column(name = "end_date")
    private LocalDateTime endDate;

    @NotBlank
    @Column(name = "status")
    private Status status;

    @OneToMany(mappedBy = "product")
    private List<ImageEntity> images;
}
