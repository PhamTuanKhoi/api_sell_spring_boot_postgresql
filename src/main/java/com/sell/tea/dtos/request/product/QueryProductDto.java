package com.sell.tea.dtos.request.product;

import com.sell.tea.dtos.gobal.dtos.QueryDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class QueryProductDto extends QueryDto {
    public String name;
    public List<Long> categoryIds;
}
