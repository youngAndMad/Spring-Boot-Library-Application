package com.example.studentmanagmentsystem.login;

import jakarta.validation.constraints.NotNull;



public class Login {

    @NotNull(message = "please enter email")
    private String email;

    @NotNull(message = "please enter password")
    private String password;

    public Login(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public Login(){}

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
}
