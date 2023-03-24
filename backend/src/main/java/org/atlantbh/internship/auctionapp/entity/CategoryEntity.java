package org.atlantbh.internship.auctionapp.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@ToString
@Table(name = "category")
public class CategoryEntity {

    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    @Column(name = "name")
    private String name;

    @Column(name = "image_url")
    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "parent_category_id")
    private CategoryEntity parentCategory;

    

}
