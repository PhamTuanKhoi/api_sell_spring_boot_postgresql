package com.sell.tea.dtos.request.auth;


import com.sell.tea.dtos.request.auth.LoginRequest;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterRequest extends LoginRequest {
        @NotBlank
        @Size(min = 3)
        private String name;

        private String avatar;
}
