package com.sell.tea.gobal.dtos;

import com.sell.tea.gobal.types.SortType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QueryDto {
    private Integer page;
    private Integer limit = 10;
    private String sortBy;
    private SortType sortType;
}

