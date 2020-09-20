package com.app.Message_Backend.repository;

import com.app.Message_Backend.entities.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {
}
