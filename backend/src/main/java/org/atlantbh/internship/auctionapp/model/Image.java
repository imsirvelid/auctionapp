package org.atlantbh.internship.auctionapp.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Data
public class Image {
    private Long id;
    private String imageUrl;
    private Boolean featured;
    private Product product;
}
