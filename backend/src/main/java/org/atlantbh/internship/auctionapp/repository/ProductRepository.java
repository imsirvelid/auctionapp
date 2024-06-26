package org.atlantbh.internship.auctionapp.repository;

import org.atlantbh.internship.auctionapp.entity.ProductEntity;
import org.atlantbh.internship.auctionapp.projection.ProductBidsInfo;
import org.atlantbh.internship.auctionapp.projection.ProductHighestBid;
import org.atlantbh.internship.auctionapp.projection.RecommendedProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long>, PagingAndSortingRepository<ProductEntity, Long> {

    @Query(value = "SELECT pe FROM ProductEntity pe JOIN FETCH pe.images im WHERE im.featured = true and pe.endDate > CURRENT_DATE",
           countQuery = "select count(pe) from ProductEntity pe left join pe.images im where im.featured = true")
    Page<ProductEntity> getProductsWithThumbnails(Pageable pageable);

    @Query("select pe from ProductEntity pe JOIN FETCH pe.images im WHERE im.featured = true and pe.endDate > CURRENT_DATE order by random() limit 1")
    ProductEntity findOneRandom();

    @Query("""
                select pe 
                from ProductEntity pe 
                where (:name is null or lower(pe.name) like lower(concat('%', :name, '%'))) and 
                      (:categoryId is null or :categoryId = pe.category.id or :categoryId = pe.category.parentCategory.id) and
                      pe.endDate > CURRENT_DATE                      
            """)
    Page<ProductEntity> searchByNameAndCategory(Pageable pageable, String name, Long categoryId);

    @Query("""
                SELECT pe.name
                FROM ProductEntity pe
                WHERE (:categoryId is null or :categoryId = pe.category.id) and levenshtein(pe.name, :name) < 6 and pe.endDate > CURRENT_DATE
                GROUP BY pe.name
                ORDER BY COUNT(pe.name) * 1.0 / (levenshtein(pe.name, :name) + 1) DESC
                LIMIT 1
            """)
    Optional<String> searchSimilarProductsName(String name, Long categoryId);

    @Query("""
            SELECT pe.name as productName, img.url as productThumbnail, pe.id as productId, pe.endDate as endDate, pe.startingPrice as myPrice, 
            (SELECT MAX(bid.price) FROM  BidEntity bid WHERE bid.product.id = pe.id) as highestPrice, 
            (SELECT COUNT(bid.id) FROM BidEntity bid WHERE bid.product.id = pe.id) as numberOfBids 
            FROM ProductEntity pe, ImageEntity img
            WHERE pe.id = img.product.id and img.featured = true and pe.user.id = :userId and pe.endDate > CURRENT_DATE
            """)
    List<ProductBidsInfo> getUserActiveProducts(Long userId);

    @Query("""
            SELECT pe.name as productName, img.url as productThumbnail, pe.id as productId, pe.endDate as endDate, pe.startingPrice as myPrice, 
            (SELECT MAX(bid.price) FROM  BidEntity bid WHERE bid.product.id = pe.id) as highestPrice, 
            (SELECT COUNT(bid.id) FROM BidEntity bid WHERE bid.product.id = pe.id) as numberOfBids 
            FROM ProductEntity pe, ImageEntity img
            WHERE pe.id = img.product.id and img.featured = true and pe.user.id = :userId and pe.endDate < CURRENT_DATE
            """)
    List<ProductBidsInfo> getUserSoldProducts(Long userId);

    @Query("""
            SELECT pe as product, be as bid FROM ProductEntity pe, BidEntity be
            WHERE be.product.id = pe.id AND be.price = (SELECT MAX(be1.price) FROM BidEntity be1 WHERE be1.product.id = pe.id) AND 
            pe.endDate <= CURRENT_DATE AND 
            NOT EXISTS (SELECT ne FROM NotificationEntity ne WHERE pe.id = ne.product.id AND ne.type = 'SUCCESS')
            """)
    List<ProductHighestBid> getEndedProductsWithoutNotification();

    @Query(value = """
             SELECT pe.name as productName, img.url as productThumbnail, pe.starting_price as productPrice
              FROM product pe, image img 
              WHERE pe.id = img.product_id AND img.featured = true AND pe.user_id <> :userId AND pe.end_date > CURRENT_DATE
              ORDER BY (
                SELECT COUNT(*)
                FROM bid be, product p1
                WHERE be.user_id = :userId AND be.product_id = p1.id AND p1.category_id = pe.category_id
              ) * 10
              + (SELECT
                ucp.count * 10 / ((EXTRACT(year FROM age(CURRENT_DATE, ucp.date_clicked)) * 12) + EXTRACT(month FROM age(CURRENT_DATE, ucp.date_clicked)) + 1)
                FROM user_clicked_products ucp, product p1
                WHERE ucp.user_id = :userId AND ucp.product_id = p1.id AND p1.category_id = pe.category_id
              ) DESC
              LIMIT 3;
            """, nativeQuery = true)
    List<RecommendedProduct> getUserRecommendedProducts(Long userId);
}
