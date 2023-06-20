package com.sell.tea.dtos.auth;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterRequest {
        @NotBlank
        @Size(min = 3)
        private String name;

        @NotEmpty
        @Email
        private String email;

        @NotBlank
        private String password;

        private String avatar;
}
