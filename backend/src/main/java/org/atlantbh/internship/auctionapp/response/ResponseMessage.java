package org.atlantbh.internship.auctionapp.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ResponseMessage {
    private Boolean successful;
    private String message;
}
