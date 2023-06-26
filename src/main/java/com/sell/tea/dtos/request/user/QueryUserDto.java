package com.sell.tea.dtos.request.user;

import com.sell.tea.dtos.gobal.QueryDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QueryUserDto extends QueryDto {
    private String name;
}
