package org.atlantbh.internship.auctionapp.request;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
@ToString
public class TextMessageDTO {
    private String message;
    private LocalDateTime date;
    private Long receiver;
}
