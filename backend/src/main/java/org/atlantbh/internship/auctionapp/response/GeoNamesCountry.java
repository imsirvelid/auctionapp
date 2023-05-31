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
    private String countryCode;
    private String countryName;
}