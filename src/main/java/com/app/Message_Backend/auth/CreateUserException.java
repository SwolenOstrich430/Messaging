package com.app.Message_Backend.auth;

import com.app.Message_Backend.entities.User;
import graphql.GraphQLException;

public class CreateUserException extends GraphQLException {
    private static final String MESSAGE_TEMPLATE = "Could not create user. %s";
    private String reason;

    public CreateUserException(String reason) {
        this.reason = reason;
    }

    public String getMessage() {

        return String.format(MESSAGE_TEMPLATE, reason);
    }

}
