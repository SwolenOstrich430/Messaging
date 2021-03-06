package com.app.Message_Backend.resolver;

import com.app.Message_Backend.entities.User;
import com.app.Message_Backend.service.UserService;
import graphql.kickstart.tools.GraphQLResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserResolver implements GraphQLResolver<User> {
    @Autowired
    private final UserService userService;

    public UserResolver(UserService userService) {
        this.userService = userService;
    }



}
