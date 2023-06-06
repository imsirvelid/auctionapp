package org.atlantbh.internship.auctionapp.service.api;

import org.atlantbh.internship.auctionapp.response.GeoNamesCountryResponse;
import reactor.core.publisher.Mono;

public interface CountryService {
    Mono<GeoNamesCountryResponse> getAllCountries();
    Mono<String> getCitiesForCountry(String countryCode);
}
