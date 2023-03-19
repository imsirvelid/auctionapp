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

    @Autowired
    ProductRepository productRepository;
    ProductMapper productMapper = Mappers.getMapper(ProductMapper.class);

    @Override
    public List<ProductResponse> getAll(int page, String sortBy) {
        Pageable paging = null;
        // This is a bit clunky, will fix later
        if (sortBy.equals("endDate")){
            paging = PageRequest.of(page, 4, Sort.by(sortBy).ascending());
        }
        else
            paging = PageRequest.of(page, 4, Sort.by(sortBy).descending());
        List<ProductEntity> entites = new ArrayList<>();
        productRepository.findAll(paging).forEach(entites::add);
        List<ProductResponse> response = entites.stream().map(productMapper::fromEntity).collect(Collectors.toList());
        return response;
    }

    @Override
    public ProductResponse getRandom() {
        List<ProductEntity> entites = new ArrayList<>();
        productRepository.findAll().forEach(entites::add);
        List<ProductResponse> response = entites.stream().map(productMapper::fromEntity).collect(Collectors.toList());
        Random rand = new Random();
        int i = rand.nextInt(response.size());
        return response.get(i);
    }
}
