package org.atlantbh.internship.auctionapp.service.impl;

import org.atlantbh.internship.auctionapp.response.GeoNamesCountryResponse;
import org.atlantbh.internship.auctionapp.service.api.CountryService;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class GeoNamesService implements CountryService {

    private final WebClient webClient;

    public GeoNamesService(WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public Mono<GeoNamesCountryResponse> getAllCountries() {
        String url = "http://api.geonames.org/countryInfoJSON?username=vimsir";

        return webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(GeoNamesCountryResponse.class);
    }

    @Override
    public Mono<String> getCitiesForCountry(String countryCode) {
        String url = "http://api.geonames.org/searchJSON?country=" + countryCode + "&username=vimsir";

        return webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(String.class);
    }
}
