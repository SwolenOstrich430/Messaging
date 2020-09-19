package com.app.Message_Backend.service;

import com.app.Message_Backend.pojo.Conversation;
import com.app.Message_Backend.pojo.User;
import com.app.Message_Backend.repository.ConversationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConversationService {

    @Autowired
    private ConversationRepository conversationRepository;

    public ConversationService() {}

    public Conversation save(Conversation conversation) {
        return conversationRepository.save(conversation);
    }


}
