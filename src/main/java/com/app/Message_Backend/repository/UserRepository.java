package com.app.Message_Backend.repository;

import com.app.Message_Backend.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByEmail(String email);
    User findUserByUsername(String username);

    List<User> findAllByIdIn(List<Long> ids);
}
