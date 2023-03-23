package org.atlantbh.internship.auctionapp.dto.Category;

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
