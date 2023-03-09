package org.atlantbh.internship.auctionapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class AuctionappApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuctionappApplication.class, args);
	}

}
