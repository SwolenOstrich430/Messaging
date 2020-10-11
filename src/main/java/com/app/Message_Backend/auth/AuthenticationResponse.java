package com.app.Message_Backend.auth;

public class AuthenticationResponse {

    private String token;
    private Long id;

    public AuthenticationResponse() { }

    public AuthenticationResponse(String token, Long id) {
        this.token = token;
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public Long getId() {
        return id;
    }
}
