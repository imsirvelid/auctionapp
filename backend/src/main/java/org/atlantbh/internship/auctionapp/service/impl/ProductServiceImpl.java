package org.atlantbh.internship.auctionapp.service.impl;

import org.atlantbh.internship.auctionapp.controller.common.PageParams;
import org.atlantbh.internship.auctionapp.controller.common.SearchParams;
import org.atlantbh.internship.auctionapp.controller.common.SortParams;
import org.atlantbh.internship.auctionapp.entity.*;
import org.atlantbh.internship.auctionapp.exception.BadRequestException;
import org.atlantbh.internship.auctionapp.model.Image;
import org.atlantbh.internship.auctionapp.model.PersonDetails;
import org.atlantbh.internship.auctionapp.model.Product;
import org.atlantbh.internship.auctionapp.projection.ProductBidsInfo;
import org.atlantbh.internship.auctionapp.projection.RecommendedProduct;
import org.atlantbh.internship.auctionapp.repository.CategoryRepository;
import org.atlantbh.internship.auctionapp.repository.ImageRepository;
import org.atlantbh.internship.auctionapp.repository.ProductRepository;
import org.atlantbh.internship.auctionapp.repository.UserClickedProductsRepository;
import org.atlantbh.internship.auctionapp.request.CreateProductRequest;
import org.atlantbh.internship.auctionapp.response.SearchProductResponse;
import org.atlantbh.internship.auctionapp.service.api.ProductService;
import org.atlantbh.internship.auctionapp.util.Jwt;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ImageRepository imageRepository;
    private final UserClickedProductsRepository userClickedProductsRepository;

    public ProductServiceImpl(final ProductRepository productRepository, final CategoryRepository categoryRepository, final ImageRepository imageRepository, UserClickedProductsRepository userClickedProductsRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.imageRepository = imageRepository;
        this.userClickedProductsRepository = userClickedProductsRepository;
    }

    @Override
    public Page<Product> getAll(PageParams pageParams, SortParams sortParams) {
        return productRepository.getProductsWithThumbnails(PageRequest.of(pageParams.getPageNumber(), pageParams.getPageSize(), sortParams.getSort()))
                .map(ProductEntity::toDomainModel);
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
    public Product createProduct(CreateProductRequest request) throws BadRequestException {
        PersonDetails user = Jwt.getCurrentUser();
        ProductEntity product = ProductEntity.fromRequest(request);
        product.setUser(UserEntity.fromPersonDetails(user));
        product.setStatus(Status.ACTIVE);
        product.setCategory(categoryRepository.findById(request.getCategoryId()).get());
        product.setImages(Collections.emptyList());
        product = productRepository.save(product);
        saveAllImagesForProduct(request.getImages(), request.getFeaturedImg(), product.getId());
        return product.toDomainModel();
    }

    @Override
    public List<Image> saveAllImagesForProduct(List<String> images, int featuredIndex, Long productId) throws BadRequestException {
        ProductEntity product = productRepository.findById(productId)
                .orElseThrow(() -> new BadRequestException("Product with given ID does not exist"));
        PersonDetails user = Jwt.getCurrentUser();
        if (user.getId() != product.getUser().getId())
            throw new BadRequestException("You don't have product with given ID");
        List<ImageEntity> imageEntities = images.stream().map(image ->
            new ImageEntity(product, image, images.indexOf(image) == featuredIndex)
        ).collect(Collectors.toList());
        List<Image> listOfImages = new ArrayList<>();
        try {
            listOfImages = imageRepository.saveAll(imageEntities)
                    .stream().map(ImageEntity::toDomainModel)
                    .collect(Collectors.toList());
        } catch(Exception e){
            e.printStackTrace();
        }
        return listOfImages;
    }

    @Override
    public Product setPurchased(Long productId) {
        ProductEntity productEntity = productRepository.findById(productId).get();
        productEntity.setPurchased(true);
        productEntity = productRepository.save(productEntity);
        return productEntity.toDomainModel();
    }

    @Override
    public List<RecommendedProduct> getRecommendedProducts(Long userId) {
        return productRepository.getUserRecommendedProducts(userId);
    }

    @Override
    public Optional<Product> findById(Long id) throws BadRequestException {
        Long currentUserId = Jwt.getCurrentUserId();
        ProductEntity product = productRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Product with given id does not exist"));
        if (currentUserId != null){
            UserClickedProducts ucp = userClickedProductsRepository.findByUserIdAndProductId(currentUserId, id).orElse(
                    new UserClickedProducts(product, currentUserId, 0, LocalDateTime.now()));
            ucp.setCount(ucp.getCount() + 1);
            ucp.setDateClicked(LocalDateTime.now());
            userClickedProductsRepository.save(ucp);
        }
        return Optional.of(product.toDomainModel());
    }
}
