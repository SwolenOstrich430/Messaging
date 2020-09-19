package com.app.Message_Backend.graphql;

import com.app.Message_Backend.auth.AuthorizationContext;
import com.app.Message_Backend.auth.JwtUtils;
import com.app.Message_Backend.pojo.User;
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

    public ContextBuilder() { }

    @Override
    public GraphQLContext build(HttpServletRequest request) {
        String potentialToken = jwtUtils.getToken(request);

        if(potentialToken == null) {
            return new AuthorizationContext(null, request);
        } else {
            User user = jwtUtils.validate(potentialToken);

            return new AuthorizationContext(user, request);
        }
    }

    @Override
    public Object get() {
        return this;
    }
}
