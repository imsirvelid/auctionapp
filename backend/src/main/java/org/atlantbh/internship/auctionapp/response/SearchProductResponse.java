package org.atlantbh.internship.auctionapp.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.atlantbh.internship.auctionapp.model.Product;
import org.springframework.data.domain.Page;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SearchProductResponse {
    Page<Product> page;
    String didYouMeanSuggestion;
}
