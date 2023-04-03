package org.atlantbh.internship.auctionapp.repository;

import org.atlantbh.internship.auctionapp.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<ProductEntity, Long>, PagingAndSortingRepository<ProductEntity, Long> {

    @Query(value = "SELECT pe FROM ProductEntity pe left join ImageEntity im on im.product.id = pe.id WHERE im.featured = true")
    Page<ProductEntity> getProductsWithThumbnails(Pageable pageable);

    @Query("select pe from ProductEntity pe JOIN FETCH pe.images im WHERE im.featured = true order by random() limit 1")
    ProductEntity findOneRandom();

    Page<ProductEntity> findByCategoryId(Pageable pageable, Long categoryId);
}
