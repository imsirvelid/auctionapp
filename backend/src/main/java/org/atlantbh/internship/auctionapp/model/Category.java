package org.atlantbh.internship.auctionapp.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Category {
    private Long id;
    private String name;
    private String imageUrl;
}
