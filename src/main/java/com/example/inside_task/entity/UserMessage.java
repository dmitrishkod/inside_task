package com.example.inside_task.entity;

import javax.persistence.*;
import java.util.Optional;

@Entity
@Table(name = "user_message")
public class UserMessage {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "message")
    private String message;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_id")
    private User userId;

    public UserMessage() {
    }

    public UserMessage(Long id, String name, String message, User userId) {
        this.id = id;
        this.name = name;
        this.message = message;
        this.userId = userId;
    }

    public UserMessage(String name, String message, User userId) {
        this.name = name;
        this.message = message;
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }
}