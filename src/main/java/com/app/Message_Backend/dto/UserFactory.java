package com.app.Message_Backend.dto;

import com.app.Message_Backend.entities.User;

public class UserFactory {

    public static User dtoToUser(UserDTO userDTO, String hash, String salt) {
        User newUser = new User(userDTO.getEmail(), userDTO.getUsername(), hash,
                userDTO.getFirstName(), userDTO.getLastName(), true, "user", salt);

        return newUser;
    }

}
