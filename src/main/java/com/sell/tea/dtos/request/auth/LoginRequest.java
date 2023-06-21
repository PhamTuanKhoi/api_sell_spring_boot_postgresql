package com.sell.tea.dtos.request.auth;

import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public class LoginRequest {
    @NotEmpty
    @Email
    private String email;

    @NotBlank
    private String password;
}
