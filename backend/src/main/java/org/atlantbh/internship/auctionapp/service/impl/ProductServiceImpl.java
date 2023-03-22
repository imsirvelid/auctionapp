package org.atlantbh.internship.auctionapp.service.impl;

import org.atlantbh.internship.auctionapp.dto.Mapper.ProductMapper;
import org.atlantbh.internship.auctionapp.dto.Product.ProductResponse;
import org.atlantbh.internship.auctionapp.model.ProductEntity;
import org.atlantbh.internship.auctionapp.repository.ProductRepository;
import org.atlantbh.internship.auctionapp.service.api.ProductService;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;
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
    public Product getRandom() {
        return productRepository.findOneRandom().toDomainModel();
    }

    @Override
    public ProductResponse getById(Long id) {
        return productMapper.fromEntity(productRepository.findById(id).get());
    }
}
