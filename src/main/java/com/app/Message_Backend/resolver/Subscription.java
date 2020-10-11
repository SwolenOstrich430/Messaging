package com.app.Message_Backend.resolver;

import com.app.Message_Backend.dto.MessageDTO;
import com.app.Message_Backend.entities.Conversation;
import com.app.Message_Backend.entities.Message;
import com.app.Message_Backend.entities.User;
import com.app.Message_Backend.publishers.ConversationPublisher;
import com.app.Message_Backend.publishers.MessagePublisher;
import com.app.Message_Backend.service.UserService;
import graphql.kickstart.tools.GraphQLSubscriptionResolver;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class Subscription implements GraphQLSubscriptionResolver {
    @Autowired
    private MessagePublisher messagePublisher;
    @Autowired
    private ConversationPublisher conversationPublisher;
    @Autowired
    private UserService userService;

    public Subscription() {
        this.messagePublisher = messagePublisher;
    }

    public Publisher<Message> sentMessage() {
        User currUser = userService.getUserFromContext();
        System.out.println("in message subscriber");
        System.out.println("subcribing username: " + currUser.getUsername());
        return messagePublisher;
    }

    public Publisher<Conversation> createdConversation() {
        User currUser = userService.getUserFromContext();
        System.out.println("in conversation subscriber");
        System.out.println("subcribing username: " + currUser.getUsername());
        return conversationPublisher;
    }

}
