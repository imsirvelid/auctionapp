package org.atlantbh.internship.auctionapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
@ResponseBody
public class BadRequestException extends Exception {
    public BadRequestException(final String message) {
        super(message);
    }
}
