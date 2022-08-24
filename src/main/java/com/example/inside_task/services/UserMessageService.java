package com.example.inside_task.services;

import com.example.inside_task.entity.User;
import com.example.inside_task.entity.UserMessage;
import com.example.inside_task.repositories.UserMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserMessageService {
    @Autowired
    private UserMessageRepository userMessageRepository;


    public List<UserMessage> list() {
        return (List<UserMessage>) userMessageRepository.findAll();
    }

    /**
     * Сохранение UserMessage
     * @param userMessage
     * @return
     */
    public UserMessage save(UserMessage userMessage) {
        return userMessageRepository.save(userMessage);
    }

    /**
     * Запрос последних 10 сообщений пользователя
     * @param user
     * @return
     */
    public List<UserMessage> getTenMessages(User user){
        Pageable limit =  PageRequest.of(0,10);
       return userMessageRepository.findAllByUserId(user, limit).getContent();
    }
}