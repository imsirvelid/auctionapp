package org.atlantbh.internship.auctionapp.service.api;

import org.atlantbh.internship.auctionapp.controller.common.PageParams;
import org.atlantbh.internship.auctionapp.controller.common.SearchParams;
import org.atlantbh.internship.auctionapp.controller.common.SortParams;
import org.atlantbh.internship.auctionapp.model.Product;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> getAll(PageParams pageParams, SortParams sortParams);
    Page<Product> search(PageParams pageParams, SortParams sortParams, SearchParams searchParams);
    Optional<Product> findById(Long id);
    Product getRandom();
}
