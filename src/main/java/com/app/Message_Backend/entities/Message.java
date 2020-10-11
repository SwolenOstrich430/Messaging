package com.app.Message_Backend.entities;

import com.app.Message_Backend.listeners.SaveListener;

import javax.persistence.*;
import java.util.Date;

@EntityListeners( SaveListener.class )
@Entity(name = "Messages")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String text;
    @Column(name = "time_sent")
    private Date timeSent;
    @ManyToOne
    @JoinColumn(name="conversation_id", nullable=false)
    private Conversation conversation;
    @Column(name = "sender_id")
    private Long senderId;

    public Message() {}

    public Message(String text, Conversation conversation, Long senderId) {
        this.text = text;
        this.conversation = conversation;
        this.senderId = senderId;
        this.timeSent = new Date();
    }

    public Message(String text, Long senderId) {
        this.text = text;
        this.senderId = senderId;
        this.timeSent = new Date();
    }

    public Message(String text, Conversation conversation, Long senderId, Date timeSent) {
        this.text = text;
        this.conversation = conversation;
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

    public Conversation getConversation() {
        return conversation;
    }

    public void setConversation(Conversation conversation) {
        this.conversation = conversation;
    }

    public Long getSenderId() {
        return senderId;
    }

    public void setSenderId(Long senderId) {
        this.senderId = senderId;
    }

    public Long getConversationId() {
        return this.conversation.getId();
    }

}
