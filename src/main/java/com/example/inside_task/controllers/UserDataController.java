package com.example.inside_task.controllers;


import com.example.inside_task.entity.UserData;
import com.example.inside_task.services.UserDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("userdata")
public class UserDataController {
    @Autowired
    private UserDataService userDataService;

    /**
     * Получение списка данных
     *
     * @return
     */
    @GetMapping(path = "/", produces = "application/json")
    public List<UserData> getUserData() {return userDataService.list();}
    //TODO получать данные по id
}
