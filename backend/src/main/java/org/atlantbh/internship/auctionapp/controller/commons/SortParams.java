package org.atlantbh.internship.auctionapp.controller.commons;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Sort;

@Getter
@Setter
@AllArgsConstructor
public class SortParams {
    private String sortOrder;
    private String sortField;

    public Sort getSort(){
        return Sort.by(Sort.Direction.fromString(sortOrder), sortField);
    }
}
