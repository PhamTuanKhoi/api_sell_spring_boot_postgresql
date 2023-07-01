package com.sell.tea.dtos.gobal.types;

import org.springframework.data.domain.Sort;

public class SortFilter {
    private String sortBy;
    private SortType sortType;

    public SortFilter(String sortBy, SortType sortType) {
        this.sortBy = sortBy;
        this.sortType = sortType;
    }

    public Sort getSortType() {
        if(sortType == null || sortBy == null)
            return Sort.by("id").descending();

        switch (sortType) {
            case ASC -> Sort.by(sortBy).ascending();
            case DESC -> Sort.by(sortBy).descending();
            default -> Sort.by("id").descending();
        }

        return Sort.by("id").descending();
    }


}
