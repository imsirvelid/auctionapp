package org.atlantbh.internship.auctionapp.service.api;

import org.atlantbh.internship.auctionapp.controller.commons.PageParams;
import org.atlantbh.internship.auctionapp.controller.commons.SortParams;
import org.atlantbh.internship.auctionapp.model.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAll(PageParams pageParams, SortParams sortParams);
    Product getById(Long id);
    Product getRandom();
}
