package org.atlantbh.internship.auctionapp.response;

public class FailedResponseMessage extends ResponseMessage {

    public FailedResponseMessage(String message) {
        super(message, false);
    }
}
