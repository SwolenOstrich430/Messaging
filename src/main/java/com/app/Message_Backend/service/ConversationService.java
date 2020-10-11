package com.app.Message_Backend.service;

import com.app.Message_Backend.entities.Conversation;
import com.app.Message_Backend.publishers.ConversationPublisher;
import com.app.Message_Backend.repository.ConversationRepository;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ConversationService {

    @Autowired
    private ConversationRepository conversationRepository;



    public ConversationService() {}

    public Conversation save(Conversation conversation, DataFetchingEnvironment env) {
        Optional<Conversation> createdConversation = Optional.ofNullable(conversationRepository.save(conversation));
        return createdConversation.get();
    }

    public Optional<Conversation> findById(Long conversationId) {
        return conversationRepository.findById(conversationId);
    }

}
