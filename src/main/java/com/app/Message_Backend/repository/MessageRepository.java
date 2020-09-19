package com.app.Message_Backend.repository;

import com.app.Message_Backend.pojo.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {
}
