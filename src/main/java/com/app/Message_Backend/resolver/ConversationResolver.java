package com.app.Message_Backend.resolver;

import com.app.Message_Backend.entities.Conversation;
import com.app.Message_Backend.service.ConversationService;
import graphql.kickstart.tools.GraphQLResolver;
import org.springframework.beans.factory.annotation.Autowired;

public class ConversationResolver implements GraphQLResolver<Conversation> {

    @Autowired
    private ConversationService conversationService;

    public ConversationResolver() {
        this.conversationService = conversationService;
    }
}
