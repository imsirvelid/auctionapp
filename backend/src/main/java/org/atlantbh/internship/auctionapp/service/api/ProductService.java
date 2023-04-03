package org.atlantbh.internship.auctionapp.service.api;

import org.atlantbh.internship.auctionapp.controller.commons.PageParams;
import org.atlantbh.internship.auctionapp.controller.commons.SortParams;
import org.atlantbh.internship.auctionapp.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> getAll(PageParams pageParams, SortParams sortParams);
    Optional<Product> findById(Long id);
    Product getRandom();
}
