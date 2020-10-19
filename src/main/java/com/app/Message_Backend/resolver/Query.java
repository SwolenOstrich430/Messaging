package com.app.Message_Backend.resolver;

import com.app.Message_Backend.auth.*;
import com.app.Message_Backend.dto.UserBuilder;
import com.app.Message_Backend.dto.UserDTO;
import com.app.Message_Backend.entities.Conversation;
import com.app.Message_Backend.entities.User;
import com.app.Message_Backend.service.UserService;
import graphql.GraphQLException;
import graphql.kickstart.tools.GraphQLQueryResolver;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class Query implements GraphQLQueryResolver {
    @Autowired
    private final UserService userService;
    @Autowired
    private final JwtUtils jwtUtils;
    @Autowired
    private final AuthenticationUtils authenticationUtils;
    @Value("${app.authentication-exception-message}")
    private String authenticationExceptionMessage;

    public Query(UserService userService, JwtUtils jwtUtils, AuthenticationUtils authenticationUtils) {
        this.userService = userService;
        this.jwtUtils = jwtUtils;
        this.authenticationUtils = authenticationUtils;
    }


    public AuthenticationResponse authenticateUser(AuthenticationRequest authRequest) {
        Optional<User> potentialUser = Optional.ofNullable(userService.findUserByEmail(authRequest.getEmail()));
        if(!potentialUser.isPresent()) {
            throw new AuthenticationException(authenticationExceptionMessage);
        }

        User user = potentialUser.get();

        if(authenticationUtils.isCorrectPassword(authRequest.getPassword(), user.getSalt(),
                user.getPassword())) {
            String jwt = jwtUtils.build(user);
            return new AuthenticationResponse(jwt, user.getId());
        } else {
            throw new AuthenticationException(authenticationExceptionMessage);
        }
    }

    public User findUserByUsername(String username) {
        Optional<User> potentialUser = userService.findUserByUsername(username);
        if(!potentialUser.isPresent()) {
            throw new GraphQLException("Could not find user " + username);
        }

        return potentialUser.get();
    }

    public List<Conversation> getConversations(DataFetchingEnvironment env) {
        Optional<User> potentialUser = Optional.ofNullable(userService.getUserFromContext());

        if(!potentialUser.isPresent()) {
            throw new GraphQLException("Unauthorized");
        }

        ArrayList<Conversation> conversations = new ArrayList<>(potentialUser.get().getConversations());
        Collections.sort(conversations);
        return conversations;
    }

}
