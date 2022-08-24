package com.example.inside_task.controllers;

import com.example.inside_task.entity.User;
import com.example.inside_task.entity.UserMessage;
import com.example.inside_task.models.UserMessageModel;
import com.example.inside_task.services.UserMessageService;
import com.example.inside_task.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("message")
public class UserMessageController {
    @Autowired
    private UserMessageService userMessageService;
    @Autowired
    private UserService userService;

    protected final String HISTORY = "history 10";

    /**
     * Получение списка сообщений всех пользователей
     *
     * @return
     */
    @GetMapping(path = "/", produces = "application/json")
    public List<UserMessage> getAllMessage() {
        return userMessageService.list();
    }

    /**
     * Сохранение сообщений от пользователя / Вызов последних 10 сообщений пользователя по ключевому слову "history 10"
     * @param newUserMessage
     * @return
     */
    @PostMapping(path = "/send", produces = "application/json")
    public ResponseEntity<List<UserMessage>> saveMessage(@RequestBody UserMessageModel newUserMessage) {
        Long userId = Long.parseLong(newUserMessage.getUser_id());
        Optional<User> user = userService.findById(userId);

        if (user.isPresent()){
            if (newUserMessage.getMessage().equals(HISTORY)){
                List<UserMessage> messages = userMessageService.getTenMessages(user.get());
                return new ResponseEntity<>(messages, HttpStatus.OK);
            }
            UserMessage userMessage = new UserMessage(newUserMessage.getName(),newUserMessage.getMessage(),user.get());
            try {
                userMessageService.save(userMessage);
                return new ResponseEntity<>(HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
