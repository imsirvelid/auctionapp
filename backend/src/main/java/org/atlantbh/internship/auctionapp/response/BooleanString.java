package org.atlantbh.internship.auctionapp.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class BooleanString {
    private Boolean successfull;
    private String message;
}
