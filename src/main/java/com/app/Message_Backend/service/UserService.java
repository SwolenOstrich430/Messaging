package com.app.Message_Backend.service;

import com.app.Message_Backend.pojo.User;
import com.app.Message_Backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserService() { }

    public User save(User user) {
        return userRepository.save(user);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }


    public Optional<List<User>> findAllByIds(List<Long> ids) {
        return Optional.of(userRepository.findAllById(ids));
    }
}
