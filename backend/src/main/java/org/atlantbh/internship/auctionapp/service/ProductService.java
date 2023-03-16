package org.atlantbh.internship.auctionapp.service;

import org.atlantbh.internship.auctionapp.dto.Product.ProductResponse;
import org.atlantbh.internship.auctionapp.model.ProductEntity;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ProductService {
    public List<ProductResponse> getAll();
}
