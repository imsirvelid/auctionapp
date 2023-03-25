
package org.atlantbh.internship.auctionapp.controller;

import org.atlantbh.internship.auctionapp.entity.Sample;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {

    @GetMapping(value="/sample")
    public Sample sampleTest(){
        Sample sample = new Sample(123L, "Radi");
        return sample;
    }
}
