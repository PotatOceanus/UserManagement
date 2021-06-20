package com.example.UserManagement;

import com.alibaba.fastjson.JSON;
import com.example.UserManagement.controller.UserController;
import com.example.UserManagement.entity.User;
import com.example.UserManagement.entity.UserInfoPush;
import com.example.UserManagement.entity.UserInfoUpdate;
import com.example.UserManagement.exceptions.UserNotFoundException;
import com.example.UserManagement.repository.UserRepository;
import com.example.UserManagement.service.impl.AddUserServiceImpl;
import com.example.UserManagement.service.impl.UpdateUserServiceImpl;
import net.minidev.json.JSONObject;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.nio.file.Path;
import java.sql.Array;
import java.util.*;
import java.util.stream.Collectors;

import static java.nio.file.Paths.get;
import static org.assertj.core.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;


public class UserControllerTestsMock {

    @Mock
    private UserRepository userRepository;

    @Mock
    private AddUserServiceImpl addUserServiceImpl;

    @Mock
    private UpdateUserServiceImpl updateUserServiceImpl;

    @InjectMocks
    UserController userController;

    private MockMvc mockMvc;

    private UserInfoPush userInfoPush;

    private UserInfoUpdate userInfoUpdate;

    @BeforeEach
    public void setupMock() {
        MockitoAnnotations.openMocks(this);
        userInfoPush = new UserInfoPush();
        userInfoUpdate = new UserInfoUpdate();
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    User user1 = new User("JackBauer@CTU.com",
            "123456",
            "Jack",
            "Bauer",
            "JackBauer@CTU.com",
            "654321",
            30,
            "male",
            "US",
            "CTU:US:Head",
            "active",
            "2021-06-16T22:10:36.858Z",
            "2021-06-16T22:10:36.858Z");

    User user2 = new User("CelinaMiles@CTU.com",
            "567890",
            "Celina",
            "Miles",
            "CelinaMiles@CTU.com",
            "098765",
            28,
            "female",
            "TW",
            "CTU:US:Assi",
            "active",
            "2021-06-17T22:10:36.858Z",
            "2021-06-17T22:10:36.858Z");

    @Test
    public void addOneUserNormal () throws Exception {
        userInfoPush.setPassword("123456");
        userInfoPush.setFirstName("Jack");
        userInfoPush.setLastName("Bauer");
        userInfoPush.setEmail("JackBauer@CTU.com");
        userInfoPush.setContactNumber("654321");
        List tag = Arrays.asList("CTU","US","Head");
        userInfoPush.setTags(tag);

//        doReturn(user1).when(addUserServiceImpl).getUserInfoFull(userInfoPush);
        doReturn(user1).when(addUserServiceImpl).getUserInfoFull(any(UserInfoPush.class));
        MvcResult result = mockMvc.perform(post("/api/user-management/add")
                .content(JSON.toJSONString(userInfoPush))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andReturn();

        // Crate user verify
        assertEquals(201, result.getResponse().getStatus());
        // Save user verify
        verify(userRepository, times(1)).save(any());
        ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);
        verify(userRepository).save(captor.capture());
        assertTrue(captor.getValue().getUsername().equals(user1.getUsername()));
    }

    @Test
    public void findOneUserNormal () throws Exception {

        Mockito.when(userRepository.findById("JackBauer@CTU.com")).thenReturn(Optional.of(user1));

        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.get("/api/user-management/find/{userName}","JackBauer@CTU.com"))
                                        .andExpect(MockMvcResultMatchers.status().isOk())
                                        .andReturn();
        assertEquals(result.getResponse().getContentAsString(), userController.findOneUser("JackBauer@CTU.com").toString());

//    }

    @Test
    public void findAllUserNormal () throws Exception {
        Mockito.when(userRepository.findAll()).thenReturn(Arrays.asList(user1,user2));
        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.get("/api/user-management/find/all"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        List<User> return_users = JSON.parseArray(result.getResponse().getContentAsString(), User.class);

        assertEquals(return_users.toString(), Arrays.asList(user1,user2).toString());
    }

//    @Test

}
