package com.app.Message_Backend.resolver;

import com.app.Message_Backend.pojo.Message;
import com.app.Message_Backend.service.MessageService;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.coxautodev.graphql.tools.GraphQLResolver;
import graphql.GraphQL;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;

public class MessageResolver implements GraphQLResolver<Message> {

    @Autowired
    private MessageService messageService;

    public MessageResolver() {
        this.messageService = messageService;
    }
}
