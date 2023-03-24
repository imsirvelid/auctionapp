package org.atlantbh.internship.auctionapp.model.Category;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CategoryResponse {
    private Long id;
    private String name;
    private String imageUrl;
}
