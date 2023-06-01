package org.atlantbh.internship.auctionapp.service.impl;

import org.atlantbh.internship.auctionapp.controller.common.PageParams;
import org.atlantbh.internship.auctionapp.controller.common.SearchParams;
import org.atlantbh.internship.auctionapp.controller.common.SortParams;
import org.atlantbh.internship.auctionapp.entity.ProductEntity;
import org.atlantbh.internship.auctionapp.entity.UserClickedProducts;
import org.atlantbh.internship.auctionapp.exception.BadRequestException;
import org.atlantbh.internship.auctionapp.model.Product;
import org.atlantbh.internship.auctionapp.projection.ProductBidsInfo;
import org.atlantbh.internship.auctionapp.repository.ProductRepository;
import org.atlantbh.internship.auctionapp.repository.UserClickedProductsRepository;
import org.atlantbh.internship.auctionapp.response.SearchProductResponse;
import org.atlantbh.internship.auctionapp.service.api.ProductService;
import org.atlantbh.internship.auctionapp.util.Jwt;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final UserClickedProductsRepository userClickedProductsRepository;

    public ProductServiceImpl(final ProductRepository productRepository, UserClickedProductsRepository userClickedProductsRepository) {
        this.productRepository = productRepository;
        this.userClickedProductsRepository = userClickedProductsRepository;
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
        if (res.isEmpty() && searchParams.getProductName() != null)
            didYouMean = productRepository.searchSimilarProductsName(searchParams.getProductName(), searchParams.getCategoryId()).orElse(null);
        return new SearchProductResponse(res.map(ProductEntity::toDomainModel), didYouMean);
    }

    @Override
    public Product getRandom() {
        return productRepository.findOneRandom().toDomainModel();
    }

    @Override
    public List<ProductBidsInfo> getUserActiveProducts(Long userId) {
        return productRepository.getUserActiveProducts(userId);
    }

    @Override
    public List<ProductBidsInfo> getUserSoldProducts(Long userId) {
        return productRepository.getUserSoldProducts(userId);
    }

    @Override
    public List<Product> getRecommendedProducts(Long userId) {
        return productRepository.getUserRecommendedProducts(userId, LocalDateTime.now().minusMonths(2)).stream()
                .map(ProductEntity::toDomainModel).collect(Collectors.toList());
    }

    @Override
    public Optional<Product> findById(Long id) throws BadRequestException {
        Long currentUserId = Jwt.getCurrentUserId();
        Optional<ProductEntity> product = productRepository.findById(id);
        if (currentUserId != null){
            UserClickedProducts ucp = userClickedProductsRepository.findByUserIdAndProductId(currentUserId, id);
            if (ucp == null)
                userClickedProductsRepository.save(new UserClickedProducts(product.orElseThrow(() -> new BadRequestException("Product with given id does not exist")),
                        currentUserId, 0, LocalDateTime.now()));
            else {
                ucp.setCount(ucp.getCount() + 1);
                ucp.setDateClicked(LocalDateTime.now());
                userClickedProductsRepository.save(ucp);
            }
        }
        return product.map(ProductEntity::toDomainModel);
    }
}
