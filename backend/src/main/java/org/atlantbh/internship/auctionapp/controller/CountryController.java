package org.atlantbh.internship.auctionapp.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.atlantbh.internship.auctionapp.response.GeoNamesCountryResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Tag(name = "Credit Card", description = "Credit Card APIs")
@RestController
@RequestMapping("/country")
public class CountryController {

    private final WebClient webClient;

    public CountryController(WebClient webClient) {
        this.webClient = webClient;
    }

    @GetMapping
    public Mono<GeoNamesCountryResponse> getAllCountries() {
        String url = "http://api.geonames.org/countryInfoJSON?username=vimsir";

        return webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(GeoNamesCountryResponse.class);
    }

    @GetMapping("/{countryCode}")
    public Mono<String> getCitiesForCountry(@PathVariable String countryCode){
        String url = "http://api.geonames.org/searchJSON?country=" + countryCode + "&username=vimsir";

        return webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(String.class);
    }
}