package com.app.Message_Backend.entities;

import com.app.Message_Backend.listeners.SaveListener;

import javax.persistence.*;
import java.util.Set;

@EntityListeners( SaveListener.class )
@Entity(name="Conversations")
public class Conversation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    @OrderBy("timeSent DESC")
    @OneToMany(mappedBy="conversation", cascade={CascadeType.ALL}, fetch = FetchType.EAGER)
    public Set<Message> messages;
    @ManyToMany(cascade = { CascadeType.MERGE }, fetch = FetchType.EAGER)
    @JoinTable(
            name="Users_Conversations",
            joinColumns = @JoinColumn(name="conversation_id"),
            inverseJoinColumns = @JoinColumn(name="user_id")
    )
    public Set<User> users;

    public Conversation() {}

    public Conversation(Long id) {
        this.id = id;
    }

    public Conversation(Set<User> users) {
        this.users = users;
    }

    public Conversation(Set<Message> messages, Set<User> users) {
       this.messages = messages;
       this.users = users;
    }

    public Long getId() {
        return id;
    }

    public Set<Message> getMessages() {
        return messages;
    }

    public void setMessages(Set<Message> messages) {
        this.messages = messages;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }


}
