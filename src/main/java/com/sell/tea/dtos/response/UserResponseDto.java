package com.sell.tea.dtos.response;


import lombok.*;


@Data
public class UserResponseDto {
    private Long id;
    private String name;
    private String email;
    private String avatar;
}
