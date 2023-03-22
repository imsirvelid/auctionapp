package org.atlantbh.internship.auctionapp.service.api;

import org.atlantbh.internship.auctionapp.dto.Product.ProductResponse;
import org.atlantbh.internship.auctionapp.model.ProductEntity;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ProductService {
    List<ProductResponse> getAll(int page, String sortBy);
    ProductResponse getRandom();
    ProductResponse getById(Long id);
}
