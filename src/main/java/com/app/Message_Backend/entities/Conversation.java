package com.app.Message_Backend.entities;

import javax.persistence.*;
import java.util.List;

@Entity(name="Conversations")
public class Conversation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    @OneToMany(mappedBy="conversation", cascade={CascadeType.ALL})
    public List<Message> messages;
    @ManyToMany(cascade = { CascadeType.MERGE })
    @JoinTable(
            name="Users_Conversations",
            joinColumns = @JoinColumn(name="conversation_id"),
            inverseJoinColumns = @JoinColumn(name="user_id")
    )
    public List<User> users;

    public Conversation() {}

    public Conversation(Long id) {
        this.id = id;
    }

    public Conversation(List<User> users) {
        this.users = users;
    }

    public Conversation(List<Message> messages, List<User> users) {
       this.messages = messages;
       this.users = users;
    }

    public Long getId() {
        return id;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
