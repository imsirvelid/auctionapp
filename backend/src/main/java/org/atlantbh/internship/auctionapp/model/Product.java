package org.atlantbh.internship.auctionapp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Product {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "")
    private User user;

    @Column(name = "name")
    private String name;

    @Column(name = "details")
    private String details;

    @Column(name = "created")
    private LocalDateTime created;

    @Column(name = "start_date")
    private LocalDateTime startDate;

    @Column(name = "end_date")
    private LocalDateTime endDate;
    
    @Column(name = "status")
    private STATUS status;
}
