package org.atlantbh.internship.auctionapp.service.api;

import org.atlantbh.internship.auctionapp.controller.common.PageParams;
import org.atlantbh.internship.auctionapp.controller.common.SearchParams;
import org.atlantbh.internship.auctionapp.controller.common.SortParams;
import org.atlantbh.internship.auctionapp.model.Product;
<<<<<<< HEAD
import org.atlantbh.internship.auctionapp.response.SearchProductResponse;
=======
import org.springframework.data.domain.Page;
>>>>>>> main

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> getAll(PageParams pageParams, SortParams sortParams);
<<<<<<< HEAD
    SearchProductResponse search(PageParams pageParams, SortParams sortParams, SearchParams searchParams);
=======
    Page<Product> search(PageParams pageParams, SortParams sortParams, SearchParams searchParams);
>>>>>>> main
    Optional<Product> findById(Long id);
    Product getRandom();
}
