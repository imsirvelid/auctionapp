package org.atlantbh.internship.auctionapp.controller.commons;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class SearchParams {
    private String productName;
    private Long categoryId;

    public SearchParams(String productName) {
        this.productName = productName;
    }

    public SearchParams(Long categoryId) {
        this.categoryId = categoryId;
    }
}
