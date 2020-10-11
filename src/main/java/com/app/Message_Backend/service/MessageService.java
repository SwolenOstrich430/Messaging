package com.app.Message_Backend.service;


import com.app.Message_Backend.entities.Conversation;
import com.app.Message_Backend.entities.Message;
import com.app.Message_Backend.publishers.MessagePublisher;
import com.app.Message_Backend.repository.MessageRepository;
import graphql.GraphQLException;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private ConversationService conversationService;
    @Autowired
    private MessagePublisher messagePublisher;

    public MessageService() {
        this.messageRepository = messageRepository;
    }

    public Message save(Message newMessage, Long conversationId, DataFetchingEnvironment env) throws Exception {
        Optional<Conversation> conversation = conversationService.findById(conversationId);

        if(!conversation.isPresent()) {
            // TODO: THROW SOME ERROR WHEN THIS ISN'T THERE
            throw new Exception("no conversation");
        }

        newMessage.setConversation(conversation.get());
        Optional<Message> potentialMessage = Optional.ofNullable(messageRepository.save(newMessage));

        if(!potentialMessage.isPresent()) {
            System.out.println("could not create message");
            throw new GraphQLException("could not create message");
        }

        return potentialMessage.get();
    }
}
