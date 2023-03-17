package org.atlantbh.internship.auctionapp.service.impl;

import org.atlantbh.internship.auctionapp.dto.Mapper.ProductMapper;
import org.atlantbh.internship.auctionapp.dto.Product.ProductResponse;
import org.atlantbh.internship.auctionapp.model.ProductEntity;
import org.atlantbh.internship.auctionapp.repository.ProductRepository;
import org.atlantbh.internship.auctionapp.service.api.ProductService;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;
    ProductMapper productMapper = Mappers.getMapper(ProductMapper.class);

    @Override
    public List<ProductResponse> getAll() {
        List<ProductEntity> entites = new ArrayList<>();
        productRepository.findAll().forEach(entites::add);
        List<ProductResponse> response = entites.stream().map(productMapper::fromEntity).collect(Collectors.toList());
        return response;
    }
}
