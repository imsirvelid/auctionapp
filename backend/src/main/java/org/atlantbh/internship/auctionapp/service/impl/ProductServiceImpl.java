package org.atlantbh.internship.auctionapp.service.impl;

import org.atlantbh.internship.auctionapp.controller.commons.PageParams;
import org.atlantbh.internship.auctionapp.controller.commons.SortParams;
import org.atlantbh.internship.auctionapp.model.Product;
import org.atlantbh.internship.auctionapp.repository.BidRepository;
import org.atlantbh.internship.auctionapp.repository.ProductRepository;
import org.atlantbh.internship.auctionapp.service.api.ProductService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final BidRepository bidRepository;

    public ProductServiceImpl(final ProductRepository productRepository, final BidRepository bidRepository) {
        this.productRepository = productRepository;
        this.bidRepository = bidRepository;
    }

    @Override
    public List<Product> getAll(PageParams pageParams, SortParams sortParams) {
        Pageable paging = PageRequest.of(pageParams.getPageNumber(), pageParams.getPageSize(), sortParams.getSort());
        return productRepository.getProductsWithThumbnails(paging);
    }

    @Override
    public Product getRandom() {
        return productRepository.findOneRandom().toDomainModel();
    }

    @Override
    public Product getById(Long id) {
        var bids = bidRepository.findById(1L);
        bids.ifPresent(bid -> System.out.println(bids.get()));
        return productRepository.findById(id).get().toDomainModel();
    }
}
