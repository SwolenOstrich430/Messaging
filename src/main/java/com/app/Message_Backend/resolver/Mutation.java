package com.app.Message_Backend.resolver;

import com.app.Message_Backend.auth.AuthenticationUtils;
import com.app.Message_Backend.auth.CreateUserException;
import com.app.Message_Backend.dto.MessageDTO;
import com.app.Message_Backend.dto.MessagesFactory;
import com.app.Message_Backend.dto.UserDTO;
import com.app.Message_Backend.dto.UserBuilder;
import com.app.Message_Backend.entities.Conversation;
import com.app.Message_Backend.entities.Message;
import com.app.Message_Backend.entities.User;
import com.app.Message_Backend.service.ConversationService;
import com.app.Message_Backend.service.MessageService;
import com.app.Message_Backend.service.UserService;
import graphql.GraphQLException;
import graphql.kickstart.tools.GraphQLMutationResolver;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Component
public class Mutation implements GraphQLMutationResolver {
    @Autowired
    private final UserService userService;
    @Autowired
    private final ConversationService conversationService;
    @Autowired
    private final MessageService messageService;
    @Autowired
    private final AuthenticationUtils authenticationUtils;


    public Mutation(UserService userService, ConversationService conversationService,
                    MessageService messageService, AuthenticationUtils authenticationUtils) {
        this.userService = userService;
        this.conversationService = conversationService;
        this.messageService = messageService;
        this.authenticationUtils = authenticationUtils;
    }

    public User createUser(UserDTO userDTO) {
        userService.validateUser(userDTO);

        final String salt = authenticationUtils.getSalt().get();
        final String hash = authenticationUtils.getHash(userDTO.getPassword(), salt).get();

        User newUser = UserBuilder.dtoToUser(userDTO, hash, salt);
        return userService.save(newUser);
    }

    public Conversation createConversation(List<Long> ids, DataFetchingEnvironment env) {
        // TODO: handle case for if some list of users is already a conversation
        Optional<User> potentialUser = Optional.ofNullable(userService.getUserFromContext());
        Set<User> foundUsers = userService.findAllByIds(ids);

        if(foundUsers.size() != ids.size()) {
            throw new GraphQLException("not all users valid");
        }

        foundUsers.add(potentialUser.get());
        Conversation newConversation = new Conversation(foundUsers);
        return conversationService.save(newConversation, env);
    }

    public Message createMessage(String text, Long conversationId, DataFetchingEnvironment env) throws Exception {
        Optional<User> potentialUser = Optional.ofNullable(userService.getUserFromContext());

        if(!potentialUser.isPresent()) {
            System.out.println("no user");
            // TODO: if I do need to do this, then actually have a module for this
            throw new GraphQLException("unauthorized");
        }

        Optional<Conversation> conversation = conversationService.findById(conversationId);

        if(!conversation.isPresent()) {
            throw new GraphQLException("could not find conversation");
        }

        Message messageToCreate = new Message(text, conversation.get(), potentialUser.get().getId());
        Message createdMessage = messageService.save(messageToCreate, conversationId, env);

        return createdMessage;
    }
}
