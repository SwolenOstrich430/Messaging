package com.app.Message_Backend.resolver;

import com.app.Message_Backend.pojo.Message;
import com.app.Message_Backend.publishers.MessagePublisher;
import com.app.Message_Backend.service.MessageService;
import com.coxautodev.graphql.tools.GraphQLSubscriptionResolver;
import io.reactivex.Observable;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class Subscription implements GraphQLSubscriptionResolver {

    @Autowired
    private MessagePublisher messagePublisher;

    public Subscription() {
        this.messagePublisher = messagePublisher;
    }

    public Publisher<Message> sentMessage(Long conversationId) {
        return messagePublisher.getPublisher(conversationId);
    }
}
