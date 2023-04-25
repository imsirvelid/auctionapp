package org.atlantbh.internship.auctionapp.repository;

import org.atlantbh.internship.auctionapp.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends CrudRepository<ProductEntity, Long>, PagingAndSortingRepository<ProductEntity, Long> {

    @Query(value = "SELECT pe FROM ProductEntity pe left join ImageEntity im on im.product.id = pe.id WHERE im.featured = true")
    Page<ProductEntity> getProductsWithThumbnails(Pageable pageable);

    @Query("select pe from ProductEntity pe JOIN FETCH pe.images im WHERE im.featured = true order by random() limit 1")
    ProductEntity findOneRandom();

    @Query("""
                select pe 
                from ProductEntity pe 
                where (:name is null or lower(pe.name) like lower(concat('%', :name, '%'))) and 
                      (:categoryId is null or :categoryId = pe.category.id)
            """)
    Page<ProductEntity> searchByNameAndCategory(Pageable pageable, String name, Long categoryId);

    @Query("""
                SELECT pe.name
                FROM ProductEntity pe
                WHERE (:categoryId is null or :categoryId = pe.category.id) and levenshtein(pe.name, :name) < 15
                GROUP BY pe.name
                ORDER BY COUNT(pe.name) * 1.0 / (levenshtein(pe.name, :name) + 1) DESC
                LIMIT 1
            """)
    Optional<String> searchSimilarProductsName(String name, Long categoryId);
}
