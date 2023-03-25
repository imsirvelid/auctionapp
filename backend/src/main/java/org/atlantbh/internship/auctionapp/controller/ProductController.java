package org.atlantbh.internship.auctionapp.controller;

import org.atlantbh.internship.auctionapp.controller.commons.PageParams;
import org.atlantbh.internship.auctionapp.controller.commons.SortParams;
import org.atlantbh.internship.auctionapp.model.Product;
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

    private final ProductService productService;

    public ProductController(final ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(params = { "page", "sort", "order"})
    public ResponseEntity<List<Product>> getProducts(@RequestParam int page, @RequestParam String sort, @RequestParam String order){
        return ResponseEntity.ok(productService.getAll(new PageParams(4, page), new SortParams(order, sort)));
    }

    @GetMapping(value = "/random")
    public ResponseEntity<Product> getRandomProduct() {
        return ResponseEntity.ok(productService.getRandom());
    }

}
