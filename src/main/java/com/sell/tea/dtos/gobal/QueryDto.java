package com.sell.tea.dtos.gobal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QueryDto {
    private Integer page;
    private Integer limit;
    private String sortBy;
    private SortType sortType;
}

 enum SortType {
    ASC,
    DESC
}
