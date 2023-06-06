package org.atlantbh.internship.auctionapp.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.atlantbh.internship.auctionapp.config.ExternalProperties;
import org.atlantbh.internship.auctionapp.properties.FirebaseProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Firebase", description = "Firebase APIs")
@RestController
@RequestMapping("/firebase")
public class FirebaseController {

    private final ExternalProperties externalProperties;

    public FirebaseController(ExternalProperties externalProperties) {
        this.externalProperties = externalProperties;
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping
    public ResponseEntity<FirebaseProperties> getFirebaseConfiguration() {
        return ResponseEntity.ok(externalProperties.getFirebase());
    }

}
