package com.app.Message_Backend.graphql;

import com.app.Message_Backend.auth.AuthorizationContext;
import com.app.Message_Backend.auth.JwtUtils;
import com.app.Message_Backend.entities.User;
import com.app.Message_Backend.service.UserService;
import graphql.servlet.DefaultGraphQLContextBuilder;
import graphql.servlet.GraphQLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.function.Supplier;

@Component
public class ContextBuilder extends DefaultGraphQLContextBuilder implements Supplier {
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private UserService userService;

    public ContextBuilder() { }

    @Override
    public GraphQLContext build(HttpServletRequest request) {
        String potentialToken = jwtUtils.getToken(request);
        System.out.println("token: " + potentialToken);
        if(potentialToken == null) {
            return new AuthorizationContext(null, request);
        } else {
            Long validatedUserId = jwtUtils.validate(potentialToken);
            User validatedUser = userService.findById(validatedUserId).get();
            System.out.println(validatedUser.getId());

            return new AuthorizationContext(validatedUser, request);
        }
    }

    @Override
    public Object get() {
        return this;
    }
}

