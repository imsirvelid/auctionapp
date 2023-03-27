package org.atlantbh.internship.auctionapp.controller;

import org.atlantbh.internship.auctionapp.controller.commons.PageParams;
import org.atlantbh.internship.auctionapp.controller.commons.SortParams;
import org.atlantbh.internship.auctionapp.model.Product;
import org.atlantbh.internship.auctionapp.service.api.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(final ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<Product> getProduct(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getById(id));
    }

    @GetMapping(params = { "pageNumber", "pageSize", "sortField", "sortOrder"})
    public ResponseEntity<List<Product>> getProducts(PageParams pageParams, SortParams sortParams){
        return ResponseEntity.ok(productService.getAll(pageParams, sortParams));
    }

    @GetMapping(value = "/random")
    public ResponseEntity<Product> getRandomProduct() {
        return ResponseEntity.ok(productService.getRandom());
    }

}
