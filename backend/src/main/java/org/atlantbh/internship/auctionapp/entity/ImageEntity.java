package org.atlantbh.internship.auctionapp.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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
    private String imageUrl;

    @NotBlank
    @Column(name = "featured")
    private Boolean featured;

    @Override
    public String toString(){
        return featured.toString();
    }

    public Image toDomainModel(){
        return new Image(this.id, this.imageUrl, this.featured, this.product.toDomainModel());
    }

    public static ImageEntity fromDomainModel(final Image image){
        final ImageEntity entity = new ImageEntity();
        entity.setFeatured(image.getFeatured());
        entity.setId(image.getId());
        entity.setImageUrl(image.getImageUrl());
        entity.setProduct(ProductEntity.fromDomainModel(image.getProduct()));
        return entity;
    }

    public Image toDomainModelWithoutProduct(){
        return new Image(this.id, this.imageUrl, this.featured, null);
    }
}
