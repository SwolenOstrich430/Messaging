package com.app.Message_Backend.auth;

import com.app.Message_Backend.entities.User;
import graphql.servlet.GraphQLContext;

import javax.servlet.http.HttpServletRequest;

public class AuthorizationContext extends GraphQLContext {

    private User user;

    public AuthorizationContext(User user, HttpServletRequest request) {
        super(request);
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
