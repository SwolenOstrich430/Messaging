package com.app.Message_Backend.repository;

import com.app.Message_Backend.entities.Conversation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConversationRepository extends JpaRepository<Conversation, Long> {
}
