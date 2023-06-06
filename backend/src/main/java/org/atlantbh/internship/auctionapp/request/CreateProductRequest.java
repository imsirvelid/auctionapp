package org.atlantbh.internship.auctionapp.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CreateProductRequest {

    @NotBlank(message = "Product name can't be empty")
    @Size(min = 2, message = "Product name must have at least 2 characters")
    @Size(max = 50, message = "Product name can't be longer than 50 characters")
    private String productName;

    @NotBlank(message = "Category can't be empty")
    private Long categoryId;

    @NotBlank(message = "Description can't be empty")
    @Size(max = 700, message = "Description can't be longer than 700 characters")
    private String description;

    @NotBlank(message = "Starting price can't be empty")
    private BigDecimal startingPrice;

    @NotBlank(message = "Start date can't be empty")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime startDate;

    @NotBlank(message = "End date can't be empty")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime endDate;

    @NotBlank(message = "Images can't be empty")
    private List<String> images;

    @NotNull(message = "Featured image can't be empty")
    private int featuredImg;

}
