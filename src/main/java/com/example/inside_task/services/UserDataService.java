package com.example.inside_task.services;

import com.example.inside_task.entity.UserData;
import com.example.inside_task.repositories.UserDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserDataService {
    @Autowired
    private UserDataRepository dataRepository;


    public List<UserData> list() {
        return dataRepository.findAll();
    }
}
