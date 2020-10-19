package com.app.Message_Backend.auth;

import graphql.GraphQLException;

public class AuthenticationException extends GraphQLException {

    public AuthenticationException(String message) {
        super(message);
    }
}
