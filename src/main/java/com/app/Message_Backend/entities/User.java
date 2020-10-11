package com.app.Message_Backend.entities;


import javax.persistence.*;
import javax.validation.constraints.Email;

import java.util.Set;

@Entity(name="Users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Email
    private String email;
    private String username;
    private String firstName;
    private String lastName;
    private String password;
    private String salt;
    private boolean active;
    private String roles;
    @ManyToMany(mappedBy = "users", fetch=FetchType.EAGER, cascade = { CascadeType.MERGE })
    private Set<Conversation> conversations;


    public User() { }

    public User(Long id, String username, String email, String roles) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.roles = roles;
    }

    public User(String email, String username, String password, String firstName, String lastName, boolean active,
                String roles, String salt) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.active = active;
        this.roles = roles;
        this.salt = salt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPassword(String password) { this.password = password; }

    public String getPassword() {
        return password;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Set<Conversation> getConversations() {
        return conversations;
    }

    @Override
    public boolean equals(Object obj) {
        Long id = ((User) obj).getId();
        System.out.println("got in equals");
        System.out.println("other id: " + id);
        System.out.println("this id: " + this.id);
        return this.id.equals(id);
    }
}
