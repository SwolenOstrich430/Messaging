package com.app.Message_Backend.dto;

import com.app.Message_Backend.entities.Conversation;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

public class MessageDTO {
    private Long id;
    private String text;
    private String timeSent;
    private Conversation conversation;
    private Long senderId;

    public MessageDTO() {}

    public MessageDTO(Long id, String text, String timeSent, Conversation conversation, Long senderId) {
        this.id = id;
        this.text = text;
        this.timeSent = timeSent;
        this.conversation = conversation;
        this.senderId = senderId;
    }

    public Long getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public String getTimeSent() {
        return timeSent;
    }

    public Conversation getConversation() {
        return conversation;
    }

    public Long getConversationId() {
        return conversation.getId();
    }

    public Long getSenderId() {
        return senderId;
    }
}
