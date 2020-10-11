package com.app.Message_Backend.dto;

import com.app.Message_Backend.entities.Message;

public class MessagesFactory {

    public static MessageDTO messageToDTO(Message message) {
        MessageDTO messageDTO = new MessageDTO(
                message.getId(),
                message.getText(),
                message.getTimeSent().toString(),
                message.getConversation(),
                message.getSenderId()
        );

        return messageDTO;
    }
}
