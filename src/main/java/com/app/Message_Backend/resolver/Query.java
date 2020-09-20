package com.app.Message_Backend.resolver;

import com.app.Message_Backend.auth.AuthenticationRequest;
import com.app.Message_Backend.auth.AuthenticationResponse;
import com.app.Message_Backend.auth.AuthenticationUtils;
import com.app.Message_Backend.auth.JwtUtils;
import com.app.Message_Backend.entities.User;
import com.app.Message_Backend.service.UserService;
import graphql.GraphQLException;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;

import java.util.*;


public class Query implements GraphQLQueryResolver {

    private final UserService userService;
    private final JwtUtils jwtUtils;
    private final AuthenticationUtils authenticationUtils;

    public Query(UserService userService, JwtUtils jwtUtils, AuthenticationUtils authenticationUtils) {
        this.userService = userService;
        this.jwtUtils = jwtUtils;
        this.authenticationUtils = authenticationUtils;
    }

    public List<User> allUsers() {
        return userService.findAll();
    }

    public AuthenticationResponse authenticateUser(AuthenticationRequest authRequest) {
        User potentialUser = userService.findUserByEmail(authRequest.getEmail());

        if(authenticationUtils.isCorrectPassword(authRequest.getPassword(), potentialUser.getSalt(),
                potentialUser.getPassword())) {
            String jwt = jwtUtils.build(potentialUser);
            return new AuthenticationResponse(jwt);
        } else {
            throw new GraphQLException("Email or password not valid");
        }
    }

}
