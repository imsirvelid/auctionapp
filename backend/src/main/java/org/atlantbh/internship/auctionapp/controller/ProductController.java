package org.atlantbh.internship.auctionapp.controller;

import org.apache.coyote.Response;
import org.atlantbh.internship.auctionapp.dto.Category.CategoryResponse;
import org.atlantbh.internship.auctionapp.dto.Product.ProductResponse;
import org.atlantbh.internship.auctionapp.service.api.CategoryService;
import org.atlantbh.internship.auctionapp.service.api.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping(value = "/products", params = { "page" })
    public ResponseEntity<List<ProductResponse>> getProducts(@RequestParam int page){
        return ResponseEntity.ok(productService.getAll(page));
    }

    @GetMapping(value = "/products/random")
    public ResponseEntity<ProductResponse> getRandomProduct() {
        return ResponseEntity.ok(productService.getRandom());
    }

    @GetMapping(value = "/products/categories")
    public ResponseEntity<List<CategoryResponse>> getParentCategories(){
        return ResponseEntity.ok(categoryService.getParentCategories());
    }
}
