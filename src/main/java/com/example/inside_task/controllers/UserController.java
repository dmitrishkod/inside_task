package com.example.inside_task.controllers;


import com.example.inside_task.entity.User;
import com.example.inside_task.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * Получение списка пользователей
     *
     * @return
     */
    @GetMapping(path = "/", produces = "application/json")
    public List<User> getUsers() {
        return userService.list();
    }
}