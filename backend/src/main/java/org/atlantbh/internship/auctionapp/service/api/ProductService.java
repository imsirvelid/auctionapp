package org.atlantbh.internship.auctionapp.service.api;

import org.atlantbh.internship.auctionapp.controller.common.PageParams;
import org.atlantbh.internship.auctionapp.controller.common.SearchParams;
import org.atlantbh.internship.auctionapp.controller.common.SortParams;
import org.atlantbh.internship.auctionapp.exception.BadRequestException;
import org.atlantbh.internship.auctionapp.model.Image;
import org.atlantbh.internship.auctionapp.model.Product;
import org.atlantbh.internship.auctionapp.projection.ProductBidsInfo;
import org.atlantbh.internship.auctionapp.request.CreateProductRequest;
import org.atlantbh.internship.auctionapp.response.SearchProductResponse;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    Page<Product> getAll(PageParams pageParams, SortParams sortParams);
    SearchProductResponse search(PageParams pageParams, SortParams sortParams, SearchParams searchParams);
    Optional<Product> findById(Long id);
    Product getRandom();
    List<ProductBidsInfo> getUserActiveProducts(Long userId);
    List<ProductBidsInfo> getUserSoldProducts(Long userId);
    Product createProduct(CreateProductRequest request) throws BadRequestException;
    List<Image> saveAllImagesForProduct(List<String> images, int featuredIndex, Long productId) throws BadRequestException;
    Product setPurchased(Long productId);
}
