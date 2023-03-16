package org.atlantbh.internship.auctionapp.dto.Mapper;

import org.atlantbh.internship.auctionapp.dto.Product.ProductResponse;
import org.atlantbh.internship.auctionapp.model.ProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    @Mapping(target = "userId", source = "user.id")
    ProductResponse fromEntity(ProductEntity entity);

    @Mapping(target = "user.id", source = "userId")
    ProductEntity toEntity(ProductResponse response);
}
