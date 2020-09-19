package com.app.Message_Backend.resolver;

import com.app.Message_Backend.auth.AuthenticationUtils;
import com.app.Message_Backend.pojo.Conversation;
import com.app.Message_Backend.pojo.Message;
import com.app.Message_Backend.pojo.User;
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
        System.out.println("got in here:");
        final String salt = authenticationUtils.getSalt().get();
        final String hash = authenticationUtils.getHash(password, salt).get();

        User newUser = new User(email, username, hash, firstName, lastName,
                    true, "user", salt);
        return userService.save(newUser);
    }

    public Conversation createConversation(List<Long> ids) {
        Optional<List<User>> potentialUsers = userService.findAllByIds(ids);

        if(!potentialUsers.isPresent()) {
            System.out.println("no users found");
        }

        Conversation newConversation = new Conversation(potentialUsers.get());
        return conversationService.save(newConversation);
    }

    public Message createMessage(String text, Long conversationId, DataFetchingEnvironment env) throws Exception {
        Optional<User> potentialUser = Optional.ofNullable(env.getContext());

        if(!potentialUser.isPresent()) {
            System.out.println("no user");
            throw new Exception("unauthorized");
        }

        Message messageToCreate = new Message(text, conversationId, potentialUser.get().getId());
        return messageService.save(messageToCreate);
    }
}
