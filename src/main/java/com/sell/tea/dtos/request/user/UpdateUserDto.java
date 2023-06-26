package com.sell.tea.dtos.request.user;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateUserDto {
    private String name;
    private String nameOld;
    private String email;
    private String emailOld;
    private String avatar;
}
