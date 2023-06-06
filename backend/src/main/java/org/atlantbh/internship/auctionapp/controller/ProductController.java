package org.atlantbh.internship.auctionapp.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.atlantbh.internship.auctionapp.controller.common.PageParams;
import org.atlantbh.internship.auctionapp.controller.common.SearchParams;
import org.atlantbh.internship.auctionapp.controller.common.SortParams;
import org.atlantbh.internship.auctionapp.exception.BadRequestException;
import org.atlantbh.internship.auctionapp.model.Product;
import org.atlantbh.internship.auctionapp.projection.ProductBidsInfo;
import org.atlantbh.internship.auctionapp.request.TextMessageDTO;
import org.atlantbh.internship.auctionapp.response.SearchProductResponse;
import org.atlantbh.internship.auctionapp.service.api.ProductService;
import org.atlantbh.internship.auctionapp.util.Jwt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Product", description = "Product APIs")
@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;


    @Autowired
    SimpMessagingTemplate template;

    public ProductController(final ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<Product> getProduct(@PathVariable Long id) throws BadRequestException {
        return productService.findById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new BadRequestException("Product is not found"));
    }

    @GetMapping()
    public ResponseEntity<List<Product>> getProducts(PageParams pageParams, SortParams sortParams) throws Exception {
        return ResponseEntity.ok(productService.getAll(pageParams, sortParams));
    }

    @GetMapping(value="/search")
    public ResponseEntity<SearchProductResponse> search(PageParams pageParams,
                                                        SortParams sortParams,
                                                        @RequestParam(required = false) String productName,
                                                        @RequestParam(required = false) Long categoryId) {
        return ResponseEntity.ok(productService.search(pageParams, sortParams, new SearchParams(productName, categoryId)));
    }

    @GetMapping(value = "/random")
    public ResponseEntity<Product> getRandomProduct() {
        return ResponseEntity.ok(productService.getRandom());
    }

    @GetMapping(value = "/user/active")
    public ResponseEntity<List<ProductBidsInfo>> getUserActiveProducts(){
        return ResponseEntity.ok(productService.getUserActiveProducts(Jwt.getCurrentUserId()));
    }

    @GetMapping(value = "/user/sold")
    public ResponseEntity<List<ProductBidsInfo>> getUserSoldProducts(){
        return ResponseEntity.ok(productService.getUserSoldProducts(Jwt.getCurrentUserId()));
    }

    @PostMapping("/send")
    public ResponseEntity<Void> sendMessage(@RequestBody TextMessageDTO textMessageDTO) {
        //template.convertAndSend("/topic/notifications", textMessageDTO);
        String username = Jwt.getCurrentUser().getUsername();
        System.out.println("Send to: " + username);
        template.convertAndSendToUser(username, "/queue", textMessageDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
