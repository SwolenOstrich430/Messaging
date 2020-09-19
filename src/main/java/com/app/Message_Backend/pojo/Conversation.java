package com.app.Message_Backend.pojo;

import javax.persistence.*;
import java.util.List;

@Entity(name="Conversations")
public class Conversation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    @OneToMany
    public List<Message> messages;
    @ManyToMany(cascade = { CascadeType.MERGE })
    @JoinTable(
            name="Users_Conversations",
            joinColumns = @JoinColumn(name="conversation_id"),
            inverseJoinColumns = @JoinColumn(name="user_id")
    )
    public List<User> users;

    public Conversation() {}

    public Conversation(List<User> users) {
        this.users = users;
    }

    public Conversation(List<Message> messages, List<User> users) {
       this.messages = messages;
       this.users = users;
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
