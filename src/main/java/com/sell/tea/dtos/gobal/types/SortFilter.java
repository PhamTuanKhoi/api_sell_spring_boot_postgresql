package com.sell.tea.dtos.gobal.types;

import org.springframework.data.domain.Sort;

public class SortFilter {
    private String sortBy;
    private String sortType;

    public SortFilter(String sortBy, String sortType) {
        this.sortBy = sortBy;
        this.sortType = sortType;
    }

    public Sort getSortType() {
        if(sortType == null || sortBy == null)
            return Sort.by("id").descending();

        switch (sortType) {
            case "ASC":
                return  Sort.by(sortBy).ascending();
            case "DESC":
                return  Sort.by(sortBy).descending();
            default:
                return Sort.by("id").descending();
        }

    }


}
