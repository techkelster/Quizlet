package com.example.demo.dto;

import com.example.demo.model.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LoginDto {
    @NotBlank(message = "User ID is required")
    private String userId;

    @NotNull(message = "Role(INSTRUCTOR, STUDENT) is required")
    private Role role;

    @NotBlank(message = "Password is required")
    private String password;
}