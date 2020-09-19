package com.app.Message_Backend.auth;

public class AuthenticationRequest {

    private String email;
    private String password;

    public AuthenticationRequest() { }

    public AuthenticationRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
