package org.atlantbh.internship.auctionapp.service.impl;

import org.atlantbh.internship.auctionapp.controller.common.PageParams;
import org.atlantbh.internship.auctionapp.controller.common.SearchParams;
import org.atlantbh.internship.auctionapp.controller.common.SortParams;
import org.atlantbh.internship.auctionapp.entity.ProductEntity;
import org.atlantbh.internship.auctionapp.model.Product;
import org.atlantbh.internship.auctionapp.repository.ProductRepository;
import org.atlantbh.internship.auctionapp.response.SearchProductResponse;
import org.atlantbh.internship.auctionapp.service.api.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(final ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getAll(PageParams pageParams, SortParams sortParams) {
        return productRepository.getProductsWithThumbnails(PageRequest.of(pageParams.getPageNumber(), pageParams.getPageSize(), sortParams.getSort()))
                .stream().map(ProductEntity::toDomainModel).collect(Collectors.toList());
    }

    @Override
    public SearchProductResponse search(PageParams pageParams, SortParams sortParams, SearchParams searchParams) {

        Page<ProductEntity> res = productRepository.searchByNameAndCategory(
                PageRequest.of(pageParams.getPageNumber(), pageParams.getPageSize(), sortParams.getSort()),
                searchParams.getProductName(), searchParams.getCategoryId()
        );
        String didYouMean = null;
        if (res.getTotalElements() <= 2)
            didYouMean = productRepository.searchSimilarProductsName(searchParams.getProductName()).get();
        return new SearchProductResponse(res.map(ProductEntity::toDomainModel), didYouMean);
    }

    @Override
    public Product getRandom() {
        return productRepository.findOneRandom().toDomainModel();
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id).map(ProductEntity::toDomainModel);
    }
}
