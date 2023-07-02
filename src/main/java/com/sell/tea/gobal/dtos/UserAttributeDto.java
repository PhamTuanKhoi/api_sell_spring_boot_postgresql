package com.sell.tea.gobal.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UserAttributeDto {
    @NotNull
    private Long userId;
}
