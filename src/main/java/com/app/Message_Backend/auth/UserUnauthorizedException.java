package com.app.Message_Backend.auth;

import graphql.GraphQLException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class UserUnauthorizedException extends GraphQLException {
    public final int status = 401;

    public UserUnauthorizedException(String message) {
        super(message);
        System.out.println("got in the unauthorized exception message");
    }

    public int getStatus() { return status; }
}
