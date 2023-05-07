package org.atlantbh.internship.auctionapp.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.atlantbh.internship.auctionapp.exception.BadRequestException;
import org.atlantbh.internship.auctionapp.model.Image;
import org.atlantbh.internship.auctionapp.service.api.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Tag(name = "File", description = "File APIs")
@RestController
@RequestMapping("/file")
public class FileController {

    // This needs to be used as env variable?
    public static String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/uploads";
    private final ProductService productService;

    public FileController(final ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/product/upload")
    public ResponseEntity<List<Image>> uploadProductImages(@RequestParam("images") MultipartFile[] images,
                                                           @RequestParam("featuredIndex") int featuredIndex,
                                                           @RequestParam("productId") Long productId) throws IOException, BadRequestException {
        ArrayList<String> filesForProduct = new ArrayList<>();
        for (int i = 0; i < images.length; i++){
            Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, "products", productId + "-" + i);
            filesForProduct.add(UPLOAD_DIRECTORY + "/products/" + productId + "-" + i);
            Files.write(fileNameAndPath, images[i].getBytes());
        }
        return ResponseEntity.ok(productService.saveAllImagesForProduct(filesForProduct, featuredIndex, productId));
    }

    @GetMapping(value = "/image")
    public @ResponseBody ResponseEntity<String> getImage() throws IOException {
        String file = UPLOAD_DIRECTORY + "/randomized_file_name-1";
        return ResponseEntity.ok(file);
    }
}
