package com.example.eliminatoriasapi.security.dtos;

import com.example.eliminatoriasapi.security.enums.RoleList;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.Set;

public class NewUser {
    @NotBlank
    private String userName;

    @Email
    private String email;

    @NotBlank
    private String password;

    private Set<RoleList> roles;

    public NewUser( @NotBlank String userName, @Email String email, @NotBlank String password) {
        this.userName = userName;
        this.email = email;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public Set<RoleList> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleList> roles) {
        this.roles = roles;
    }

}
