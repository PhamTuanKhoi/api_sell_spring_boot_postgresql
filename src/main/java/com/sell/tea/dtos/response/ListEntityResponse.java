package com.sell.tea.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class ListEntityResponse<T> {
    private List<T> entities;
    private Long count;
    private Integer limit;
    private Integer page;


}
