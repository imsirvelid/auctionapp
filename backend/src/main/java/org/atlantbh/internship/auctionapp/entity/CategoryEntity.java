package org.atlantbh.internship.auctionapp.entity;

import jakarta.persistence.*;
import lombok.*;
import org.atlantbh.internship.auctionapp.model.Category;

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

    @Column(name = "name")
    private String name;

    @Column(name = "image_url")
    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "parent_category_id")
    private CategoryEntity parentCategory;

    public Category toDomainModel(){
        return new Category(id, name, imageUrl);
    }
    

}
