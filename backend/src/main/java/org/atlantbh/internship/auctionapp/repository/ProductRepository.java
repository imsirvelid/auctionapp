package org.atlantbh.internship.auctionapp.repository;

import jakarta.persistence.Tuple;
import org.atlantbh.internship.auctionapp.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    @Query("select levenshtein(pe.name, :name) as distance, pe.name from ProductEntity pe order by distance asc")
    List<Tuple> similarProducts(String name);
}
