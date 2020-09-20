package com.app.Message_Backend.resolver;

import com.app.Message_Backend.auth.AuthenticationUtils;
import com.app.Message_Backend.auth.AuthorizationContext;
import com.app.Message_Backend.entities.Conversation;
import com.app.Message_Backend.entities.Message;
import com.app.Message_Backend.entities.User;
import com.app.Message_Backend.service.ConversationService;
import com.app.Message_Backend.service.MessageService;
import com.app.Message_Backend.service.UserService;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import graphql.schema.DataFetchingEnvironment;

import java.util.List;
import java.util.Optional;


public class Mutation implements GraphQLMutationResolver {

    private final UserService userService;
    private final ConversationService conversationService;
    private final MessageService messageService;
    private final AuthenticationUtils authenticationUtils;


    public Mutation(UserService userService, ConversationService conversationService,
                    MessageService messageService, AuthenticationUtils authenticationUtils) {
        this.userService = userService;
        this.conversationService = conversationService;
        this.messageService = messageService;
        this.authenticationUtils = authenticationUtils;
    }

    public User createUser(String email, String username, String password,
                           String firstName, String lastName) {
        final String salt = authenticationUtils.getSalt().get();
        final String hash = authenticationUtils.getHash(password, salt).get();

        // TODO: actually do something with roles
        User newUser = new User(email, username, hash, firstName, lastName,
                    true, "user", salt);
        return userService.save(newUser);
    }

    public Conversation createConversation(List<Long> ids) {
        // TODO: handle case for if some list of users is already a conversation
        Optional<List<User>> potentialUsers = userService.findAllByIds(ids);

        if(!potentialUsers.isPresent()) {
            // TODO: handle case for if users are there or not
            // TODO: handle case for if some users are invalid
            System.out.println("no users found");
        }

        Conversation newConversation = new Conversation(potentialUsers.get());
        return conversationService.save(newConversation);
    }

    public Message createMessage(String text, Long conversationId, DataFetchingEnvironment env) throws Exception {
        // TODO: do I need to handle this here or does auth context do that for me....?
        // TODO: can just make this a protected route...?
        // TODO: Put this into a module or something
        AuthorizationContext authorizationContext = env.getContext();
        Optional<User> potentialUser = Optional.ofNullable(authorizationContext.getUser());

        if(!potentialUser.isPresent()) {
            System.out.println("no user");
            // TODO: if I do need to do this, then actually have a module for this
            throw new Exception("unauthorized");
        }

        Message messageToCreate = new Message(text, potentialUser.get().getId());
        return messageService.save(messageToCreate, conversationId, env);
    }
}
