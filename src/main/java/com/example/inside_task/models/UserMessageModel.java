package com.example.inside_task.models;


public class UserMessageModel {
    private String name;
    private String message;
    private String user_id;

    public UserMessageModel(String name, String message, String user_id) {
        this.name = name;
        this.message = message;
        this.user_id = user_id;
    }

    public UserMessageModel() {
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

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
}
