package org.atlantbh.internship.auctionapp.projection;

import org.atlantbh.internship.auctionapp.entity.BidEntity;
import org.atlantbh.internship.auctionapp.entity.ProductEntity;

public interface ProductHighestBid {
    ProductEntity getProduct();
    BidEntity getBid();
}
