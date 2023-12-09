package com.example.eliminatoriasapi.security.dtos;

import jakarta.validation.constraints.NotBlank;


public class LoginUser {
    @NotBlank
    private String userName;
    @NotBlank
    private String password;

    public String getUserName() {
        return userName;
    }
    public String getPassword() {
        return password;
    }
}
