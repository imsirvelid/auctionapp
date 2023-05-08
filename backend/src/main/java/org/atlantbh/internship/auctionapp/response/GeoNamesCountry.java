package org.atlantbh.internship.auctionapp.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GeoNamesCountry {
    private String continent;
    private String capital;
    private String languages;
    private Long geonameId;
    private Double south;
    private String isoAlpha3;
    private Double north;
    private String fipsCode;
    private String population;
    private Double east;
    private String isoNumeric;
    private String areaInSqKm;
    private String countryCode;
    private Double west;
    private String countryName;
    private String postalCode;
    private String continentName;
    private String currencyCode;
}