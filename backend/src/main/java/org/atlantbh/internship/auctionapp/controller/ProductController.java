package org.atlantbh.internship.auctionapp.controller;

import org.atlantbh.internship.auctionapp.model.Product.ProductResponse;
import org.atlantbh.internship.auctionapp.service.api.CategoryService;
import org.atlantbh.internship.auctionapp.service.api.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping(value = "/products", params = { "page", "sort"})
    public ResponseEntity<List<ProductResponse>> getProducts(@RequestParam int page, @RequestParam String sort){
        return ResponseEntity.ok(productService.getAll(page, sort));
    }

    @GetMapping(value = "/product/{id}")
    public ResponseEntity<ProductResponse> getProduct(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getById(id));
    }

    @GetMapping(value = "/products/random")
    public ResponseEntity<ProductResponse> getRandomProduct() {
        return ResponseEntity.ok(productService.getRandom());
    }

}
