package org.atlantbh.internship.auctionapp.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.atlantbh.internship.auctionapp.model.Image;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "image")
public class ImageEntity {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductEntity product;

    @NotBlank
    @Column(name = "url")
    private String url;

    @NotNull
    @Column(name = "featured")
    private Boolean featured;

    @Override
    public String toString(){
        return featured.toString();
    }

    public Image toDomainModel(){
        return new Image(this.id, this.url, this.featured, this.product.toDomainModel());
    }

    public ImageEntity(ProductEntity product, String url, Boolean featured) {
        this.product = product;
        this.url = url;
        this.featured = featured;
    }

    public static ImageEntity fromDomainModel(final Image image){
        final ImageEntity entity = new ImageEntity();
        entity.setFeatured(image.getFeatured());
        entity.setId(image.getId());
        entity.setUrl(image.getUrl());
        entity.setProduct(ProductEntity.fromDomainModel(image.getProduct()));
        return entity;
    }

    public Image toDomainModelWithoutProduct(){
        return new Image(this.id, this.url, this.featured, null);
    }
}
