package com.app.Message_Backend.pojo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity(name = "Messages")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String text;
    private Date timeSent;
    private Long conversationId;
    private Long senderId;

    public Message() {}

    public Message(String text, Long conversationId, Long senderId) {
        this.text = text;
        this.conversationId = conversationId;
        this.senderId = senderId;
    }

    public Message(String text, Long conversationId, Long senderId, Date timeSent) {
        this.text = text;
        this.conversationId = conversationId;
        this.senderId = senderId;
        this.timeSent = timeSent;
    }


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Long getId() {
        return id;
    }

    public Date getTimeSent() {
        return timeSent;
    }

    public void setTimeSent(Date timeSent) {
        this.timeSent = timeSent;
    }

    public Long getConversationId() {
        return conversationId;
    }

    public void setConversationId(Long conversationId) {
        this.conversationId = conversationId;
    }

    public Long getSenderId() {
        return senderId;
    }

    public void setSenderId(Long senderId) {
        this.senderId = senderId;
    }
}
