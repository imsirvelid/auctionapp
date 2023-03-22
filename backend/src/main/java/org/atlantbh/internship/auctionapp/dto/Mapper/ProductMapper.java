package org.atlantbh.internship.auctionapp.dto.Mapper;

import org.atlantbh.internship.auctionapp.dto.Product.ProductResponse;
import org.atlantbh.internship.auctionapp.model.ImageEntity;
import org.atlantbh.internship.auctionapp.model.ProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "images", source = "images", qualifiedByName = "imageEntitiesToString")
    ProductResponse fromEntity(ProductEntity entity);

    /*@Mapping(target = "user.id", source = "userId")
    ProductEntity toEntity(ProductResponse response);*/

    @Named("imageEntitiesToString")
    static List<String> imageEntitiesToString(List<ImageEntity> imageEntities){
        ArrayList<String> strings = new ArrayList<>();
        for (var image : imageEntities)
            strings.add(image.getImageUrl());
        return strings;
    }
}
