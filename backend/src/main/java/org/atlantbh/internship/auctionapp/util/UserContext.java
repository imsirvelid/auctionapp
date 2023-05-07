package org.atlantbh.internship.auctionapp.util;

import org.atlantbh.internship.auctionapp.model.PersonDetails;
import org.springframework.security.core.context.SecurityContextHolder;

public class UserContext {

    public static Long getCurrentUserId(){
        return getCurrentUser().getId();
    }

    public static PersonDetails getCurrentUser() {
        PersonDetails personDetails = (PersonDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return personDetails;
    }
}
