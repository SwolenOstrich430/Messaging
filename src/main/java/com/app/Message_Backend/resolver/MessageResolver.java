package com.app.Message_Backend.resolver;

import com.app.Message_Backend.entities.Message;
import com.app.Message_Backend.service.MessageService;
import com.coxautodev.graphql.tools.GraphQLResolver;
import org.springframework.beans.factory.annotation.Autowired;

public class MessageResolver implements GraphQLResolver<Message> {

    @Autowired
    private MessageService messageService;

    public MessageResolver() {
        this.messageService = messageService;
    }
}
