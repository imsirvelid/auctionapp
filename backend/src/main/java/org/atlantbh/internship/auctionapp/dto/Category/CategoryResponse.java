package org.atlantbh.internship.auctionapp.dto.Category;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import org.atlantbh.internship.auctionapp.model.STATUS;
import org.atlantbh.internship.auctionapp.model.UserEntity;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CategoryResponse {
    private Long id;
    private String name;
    private String imageUrl;
}
