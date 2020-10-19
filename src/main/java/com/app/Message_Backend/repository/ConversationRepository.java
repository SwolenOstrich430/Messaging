package com.app.Message_Backend.repository;

import com.app.Message_Backend.entities.Conversation;
import com.app.Message_Backend.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface ConversationRepository extends JpaRepository<Conversation, Long> {
    Conversation findByUsersIn(Set<User> foundUsers);
}
