package org.atlantbh.internship.auctionapp.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.atlantbh.internship.auctionapp.response.GeoNamesCountryResponse;
import org.atlantbh.internship.auctionapp.service.api.CountryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Tag(name = "Credit Card", description = "Credit Card APIs")
@RestController
@RequestMapping("/country")
public class CountryController {

    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping
    public Mono<GeoNamesCountryResponse> getAllCountries() {
        return countryService.getAllCountries();
    }

    @GetMapping("/{countryCode}")
    public Mono<String> getCitiesForCountry(@PathVariable String countryCode){
        return countryService.getCitiesForCountry(countryCode);
    }
}