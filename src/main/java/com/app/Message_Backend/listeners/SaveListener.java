package com.app.Message_Backend.listeners;

import com.app.Message_Backend.entities.Conversation;
import com.app.Message_Backend.entities.Message;
import com.app.Message_Backend.publishers.ConversationPublisher;
import com.app.Message_Backend.publishers.MessagePublisher;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.stereotype.Component;

import javax.persistence.PostPersist;

@Component
public class SaveListener {
    @Autowired
    private ConversationPublisher conversationPublisher;
    @Autowired
    private MessagePublisher messagePublisher;

    public SaveListener(ConversationPublisher conversationPublisher) {
        this.conversationPublisher = conversationPublisher;
    }

    public SaveListener() {}

    @PostPersist
    public void onSave(Object entity) throws HibernateException {
        System.out.println("something was inserted");

        if(entity instanceof Conversation) {
            System.out.println("it was a conversation");
            Conversation conversation = (Conversation) entity;
            conversationPublisher.publishConversation(conversation);
        }

        if(entity instanceof Message) {
            System.out.println("it was a message");
            Message message = (Message) entity;
            messagePublisher.publishMessage(message);
        }
    }

}
