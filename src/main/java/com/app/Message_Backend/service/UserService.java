package com.app.Message_Backend.service;

import com.app.Message_Backend.auth.UserDetailsImp;
import com.app.Message_Backend.entities.User;
import com.app.Message_Backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    public UserService() { }

    public User save(User user) {
        return userRepository.save(user);
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    public Set<User> findAll() {
        return (Set<User>) userRepository.findAll();
    }

    public User findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    public Optional<User> findUserByUsername(String username) {
        return Optional.ofNullable(userRepository.findUserByUsername(username));
    }


    public Set<User> findAllByIds(List<Long> ids) {
        List<User> users = userRepository.findAllByIdIn(ids);
        Set<User> uniqueUsers = new HashSet<User>();
        users.forEach(user -> uniqueUsers.add(user));

        return uniqueUsers;
    }

    public User getUserFromContext() {
        SecurityContext context = SecurityContextHolder.getContext();
        String identifier = context.getAuthentication().getName();

        System.out.println(identifier);
        return userRepository.findUserByUsername(identifier);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> potentialUser = findUserByUsername(username);

        if(!potentialUser.isPresent()) {
            throw new UsernameNotFoundException("could not find " + username);
        }

        User user = potentialUser.get();
        return new UserDetailsImp(user.getUsername(), user.getPassword());
    }
}
