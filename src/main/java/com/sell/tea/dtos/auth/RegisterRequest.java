package com.sell.tea.dtos.auth;


import lombok.Data;

@Data
public class RegisterRequest {
        private String name;

        private String email;

        private String password;

        private String avatar;
}
