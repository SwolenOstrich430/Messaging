package com.app.Message_Backend.service;

import com.app.Message_Backend.auth.CreateUserException;
import com.app.Message_Backend.auth.UserDetailsImp;
import com.app.Message_Backend.auth.UserUnauthorizedException;
import com.app.Message_Backend.dto.UserDTO;
import com.app.Message_Backend.entities.User;
import com.app.Message_Backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
    @Value("${app.duplicate-user-message}")
    private String duplicateUserMessage;
    @Value("${app.unauthorized-exception-message}")
    private String unauthorizedMessage;

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

    public User getUserFromContext() throws UserUnauthorizedException {
        SecurityContext context = SecurityContextHolder.getContext();
        if(context.getAuthentication() == null) {
            System.out.println("user was null and we threw an exception");
            throw new UserUnauthorizedException(unauthorizedMessage);
        }

        String identifier = context.getAuthentication().getName();

        System.out.println(identifier);
        User user = userRepository.findUserByUsername(identifier);

        return user;
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

    public void validateUser(UserDTO userDTO) throws CreateUserException {
        Optional<User> userFromEmailCheck = Optional.ofNullable(userRepository.findUserByEmail(userDTO.getEmail()));
        if(userFromEmailCheck.isPresent()) {
            throw new CreateUserException(String.format(duplicateUserMessage,
                    "email", userFromEmailCheck.get().getEmail()));
        }

        Optional<User> userFromUsernameCheck = Optional.ofNullable(userRepository.findUserByUsername(
                userDTO.getUsername()));
        if(userFromUsernameCheck.isPresent()) {
            throw new CreateUserException(String.format(duplicateUserMessage, "username",
                    userFromUsernameCheck.get().getUsername()));
        }
    }
}
