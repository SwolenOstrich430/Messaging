package com.app.Message_Backend.entities;

import com.app.Message_Backend.listeners.SaveListener;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Set;

@EntityListeners( SaveListener.class )
@Entity(name="Conversations")
public class Conversation implements Comparable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    @OrderBy("timeSent ASC")
    @OneToMany(mappedBy="conversation", cascade={CascadeType.ALL}, fetch = FetchType.EAGER)
    public Set<Message> messages;
    @ManyToMany(cascade = { CascadeType.MERGE }, fetch = FetchType.EAGER)
    @JoinTable(
            name="Users_Conversations",
            joinColumns = @JoinColumn(name="conversation_id"),
            inverseJoinColumns = @JoinColumn(name="user_id")
    )
    @Column(unique = true)
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

    @Override
    public int compareTo(@NotNull Object obj) {
        Conversation conv2 = (Conversation) obj;
        int conv1MessageLength = messages.size();
        int conv2MessageLength = conv2.getMessages().size();

        if(conv1MessageLength == 0 && conv2MessageLength == 0) return 0;
        if(conv1MessageLength == 0) return 1;
        if(conv2MessageLength == 0) return -1;

        Date conv1LastMessageSentDate = new ArrayList<Message>(messages).get(conv1MessageLength - 1).getTimeSent();
        Date conv2LastMessageSentDate = new ArrayList<Message>(conv2.getMessages()).get(conv2MessageLength - 1).getTimeSent();
        return conv1LastMessageSentDate.compareTo(conv2LastMessageSentDate) * -1;
    }
}
