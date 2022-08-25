package com.example.inside_task;

import com.example.inside_task.entity.User;
import com.example.inside_task.entity.UserData;
import com.example.inside_task.entity.UserMessage;
import com.example.inside_task.models.UserMessageModel;
import com.example.inside_task.repositories.UserDataRepository;
import com.example.inside_task.repositories.UserMessageRepository;
import com.example.inside_task.repositories.UserRepository;
import com.example.inside_task.security.JwtTokenRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import javax.transaction.Transactional;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("test")
class InsideTaskApplicationTests {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserDataRepository userDataRepository;

    @Autowired
    private UserMessageRepository userMessageRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private JwtTokenRepository tokenRepository;

    @BeforeEach
    void setup(){

        userRepository.deleteAll();
        userDataRepository.deleteAll();
        userMessageRepository.deleteAll();
    }

    @Test
    void contextLoads() {
    }

    @Test
    public void givenListOfUsers_whenGetAllUsers_thenReturnUsersList() throws Exception{
        String token = tokenRepository.createToken();
        // given - предварительное условие или настройка
        List<User> listOfUsers = new ArrayList<>();
        listOfUsers.add(User.builder().login("remesh123").password("qwerty123").firstname("Ramesh").lastname("Fadatare").age(23).build());
        listOfUsers.add(User.builder().login("mihail322").password("qwerty322").firstname("Mihail").lastname("Petrov").age(23).build());
        userRepository.saveAll(listOfUsers);
        // when -  действие или поведение, которое мы собираемся тестировать
        ResultActions response = mockMvc.perform(get("/users/").header("x-csrf-token", token));

        // then - проверяем вывод
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.size()",
                        is(listOfUsers.size())));

    }

    @Test
    @Transactional
    public void givenListOfUsersData_whenGetAllUsersData_thenReturnUsersDataList() throws Exception{
        String token = tokenRepository.createToken();
        //given - предварительное условие или настройка
        List<User> listOfUsers = new ArrayList<>();
        User user1 = User.builder().login("remesh123").password("qwerty123").firstname("Ramesh").lastname("Fadatare").age(23).build();
        User user2 = User.builder().login("mihail322").password("qwerty322").firstname("Mihail").lastname("Petrov").age(23).build();
        listOfUsers.add(user1);
        listOfUsers.add(user2);
        userRepository.saveAll(listOfUsers);

        List<UserData> listOfUsersData = new ArrayList<>();
        listOfUsersData.add(UserData.builder().user(user1).name("qwerty123").address("Moscow..").mail("uuuuu@mail.ru").build());
        listOfUsersData.add(UserData.builder().user(user2).name("qwerty222").address("Samara..").mail("gun@mail.ru").build());
        userDataRepository.saveAll(listOfUsersData);
        // when -  действие или поведение, которое мы собираемся тестировать
        ResultActions response = mockMvc.perform(get("/userdata/").header("x-csrf-token", token));

        // then - проверяем вывод
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.size()",
                        is(listOfUsersData.size())));

    }

    @Test
    @Transactional
    public void givenListOfUsersMessage_whenGetAllUsersMessage_thenReturnUsersMessageList() throws Exception{
        String token = tokenRepository.createToken();
        //given - предварительное условие или настройка
        List<User> listOfUsers = new ArrayList<>();
        User user1 = User.builder().login("remesh123").password("qwerty123").firstname("Ramesh").lastname("Fadatare").age(23).build();
        User user2 = User.builder().login("mihail322").password("qwerty322").firstname("Mihail").lastname("Petrov").age(23).build();
        listOfUsers.add(user1);
        listOfUsers.add(user2);
        userRepository.saveAll(listOfUsers);

        List<UserMessage> listOfUsersMessage = new ArrayList<>();
        listOfUsersMessage.add(UserMessage.builder().userId(user1).message("Hello world").name("Ramesh").build());
        listOfUsersMessage.add(UserMessage.builder().userId(user2).message("Hello world2").name("Mishgun").build());
        userMessageRepository.saveAll(listOfUsersMessage);
        // when -  действие или поведение, которое мы собираемся тестировать
        ResultActions response = mockMvc.perform(get("/message/").header("x-csrf-token", token));

        // then - проверяем вывод
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.size()",
                        is(listOfUsersMessage.size())));

    }

    @Test
    @Transactional
    public void givenListOfUsersMessage_whenSaveMessage_thenReturn10UsersMessages() throws Exception{
        String token = tokenRepository.createToken();
        //given - предварительное условие или настройка
        User user1 = User.builder().login("remesh123").password("qwerty123").firstname("Ramesh").lastname("Fadatare").age(23).build();
        userRepository.save(user1);

        List<UserMessage> listOfUsersMessage = new ArrayList<>();
        for (int i = 0; i < 15; i++){
            listOfUsersMessage.add(UserMessage.builder().userId(user1).message("Hello world").name("Ramesh").build());
        }
        userMessageRepository.saveAll(listOfUsersMessage);
        UserMessageModel userMessageModel = new UserMessageModel("Ramesh","history 10", user1.getId().toString());
        // when -  действие или поведение, которое мы собираемся тестировать
        ResultActions response = mockMvc.perform(post("/message/send")
                .header("x-csrf-token", token)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userMessageModel)));

        // then - проверяем вывод
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.size()",
                        is(10)));

    }

    @Test
    @Transactional
    public void givenListOfUserMessage_whenSaveMessage_thenStatusOk() throws Exception{
        String token = tokenRepository.createToken();
        //given - предварительное условие или настройка
        User user1 = User.builder().login("remesh123").password("qwerty123").firstname("Ramesh").lastname("Fadatare").age(23).build();
        userRepository.save(user1);

        UserMessageModel userMessageModel = new UserMessageModel("Ramesh","Hello World", user1.getId().toString());
        // when -  действие или поведение, которое мы собираемся тестировать
        ResultActions response = mockMvc.perform(post("/message/send")
                .header("x-csrf-token", token)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userMessageModel)));

        // then - проверяем вывод
        response.andExpect(status().isOk());

    }


}
