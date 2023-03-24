package org.atlantbh.internship.auctionapp.service.impl;

import org.atlantbh.internship.auctionapp.model.Mapper.ProductMapper;
import org.atlantbh.internship.auctionapp.model.Product.ProductResponse;
import org.atlantbh.internship.auctionapp.repository.ProductRepository;
import org.atlantbh.internship.auctionapp.service.api.ProductService;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;

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
        List<ProductResponse> entites = productRepository.getProductsWithThumbnails(paging);
        return entites;
    }

    @Override
    public ProductResponse getRandom() {
        var random = productRepository.findOneRandom();
        return productMapper.fromEntity(random);
    }
}
