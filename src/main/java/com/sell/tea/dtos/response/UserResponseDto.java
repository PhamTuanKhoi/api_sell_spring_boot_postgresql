package com.sell.tea.dtos.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class UserResponseDto {
    private String name;
    private String email;
    private String avatar;
}
