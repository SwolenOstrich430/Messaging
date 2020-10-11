package com.app.Message_Backend.auth;

import graphql.kickstart.execution.subscriptions.SubscriptionSession;
import graphql.kickstart.execution.subscriptions.apollo.ApolloSubscriptionConnectionListener;
import graphql.kickstart.execution.subscriptions.apollo.OperationMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;

@Component
public class AuthenticationConnectionListener implements ApolloSubscriptionConnectionListener {
    @Autowired
    private JwtUtils jwtUtils;

    public void onConnect(SubscriptionSession session, OperationMessage message) {
        Map<String, String> params = (Map<String, String>) message.getPayload();
        System.out.println("got in here");
        Optional<String> token = Optional.ofNullable(params.get("authToken"));
        System.out.println("Token: " + token.get());
        UserDetailsImp userDetails = jwtUtils.validate(token.get());

        SecurityContextHolder.getContext().setAuthentication(new JWTPreAuthenticationToken(userDetails, null));
    }
}
