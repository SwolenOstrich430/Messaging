package com.app.Message_Backend.service;


import com.app.Message_Backend.pojo.Message;
import com.app.Message_Backend.publishers.MessagePublisher;
import com.app.Message_Backend.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private MessagePublisher messagePublisher;

    public MessageService() {
        this.messageRepository = messageRepository;
    }

    public Message save(Message newMessage) {
        Optional<Message> potentialMessage = Optional.ofNullable(messageRepository.save(newMessage));

        if(!potentialMessage.isPresent()) {
            System.out.println("could not create message");
            return null;
        }

        messagePublisher.publish(potentialMessage.get());
        return potentialMessage.get();
    }
}
