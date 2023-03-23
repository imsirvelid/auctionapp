package org.atlantbh.internship.auctionapp.controller;

import org.atlantbh.internship.auctionapp.dto.Product.ProductResponse;
import org.atlantbh.internship.auctionapp.service.api.CategoryService;
import org.atlantbh.internship.auctionapp.service.api.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping(value = "", params = { "page", "sort"})
    public ResponseEntity<List<ProductResponse>> getProducts(@RequestParam int page, @RequestParam String sort){
        return ResponseEntity.ok(productService.getAll(page, sort));
    }

    @GetMapping(value = "/random")
    public ResponseEntity<ProductResponse> getRandomProduct() {
        return ResponseEntity.ok(productService.getRandom());
    }

}
