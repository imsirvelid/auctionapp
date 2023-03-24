package org.atlantbh.internship.auctionapp.service.api;

import org.atlantbh.internship.auctionapp.model.Product.ProductResponse;

import java.util.List;

public interface ProductService {
    public List<ProductResponse> getAll(int page, String sortBy);
    public ProductResponse getRandom();
}
