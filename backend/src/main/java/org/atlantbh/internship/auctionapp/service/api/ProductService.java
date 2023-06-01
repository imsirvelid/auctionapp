package org.atlantbh.internship.auctionapp.service.api;

import org.atlantbh.internship.auctionapp.controller.common.PageParams;
import org.atlantbh.internship.auctionapp.controller.common.SearchParams;
import org.atlantbh.internship.auctionapp.controller.common.SortParams;
import org.atlantbh.internship.auctionapp.exception.BadRequestException;
import org.atlantbh.internship.auctionapp.model.Product;
import org.atlantbh.internship.auctionapp.projection.ProductBidsInfo;
import org.atlantbh.internship.auctionapp.response.SearchProductResponse;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> getAll(PageParams pageParams, SortParams sortParams);
    SearchProductResponse search(PageParams pageParams, SortParams sortParams, SearchParams searchParams);
    Optional<Product> findById(Long id) throws BadRequestException;
    Product getRandom();
    List<Product> getRecommendedProducts(Long userId);
    List<ProductBidsInfo> getUserActiveProducts(Long userId);
    List<ProductBidsInfo> getUserSoldProducts(Long userId);
}
