package com.example.UserManagement;

import com.example.UserManagement.controller.UserController;
import com.example.UserManagement.entity.User;
import com.example.UserManagement.entity.UserInfoPush;
import com.example.UserManagement.entity.UserInfoUpdate;
import com.example.UserManagement.exceptions.UserNotFoundException;
import com.example.UserManagement.repository.UserRepository;
import com.example.UserManagement.service.impl.AddUserServiceImpl;
import com.example.UserManagement.service.impl.UpdateUserServiceImpl;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
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
import java.util.Optional;

import static java.nio.file.Paths.get;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration
//@WebAppConfiguration
public class UserControllerTestsMock {

    private MockMvc mockMvc;

    @Mock
    private UserRepository userRepository;

    @Mock
    private AddUserServiceImpl addUserServiceImpl;

    @Mock
    private UpdateUserServiceImpl updateUserServiceImpl;

    @InjectMocks
    UserController userController;

    private UserInfoPush userInfoPush;

    private UserInfoUpdate userInfoUpdate;

    @BeforeEach
    public void setupMock() {
        MockitoAnnotations.openMocks(this);
        userInfoPush = new UserInfoPush();
        userInfoUpdate = new UserInfoUpdate();
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }


//    @Ignore
//    @Test
//    public void addOneUserNormal () {
//
//
//    }

    @Test
    public void findOneUserNormal () throws Exception {
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

        Mockito.when(userRepository.findById("JackBauer@CTU.com")).thenReturn(Optional.of(user1));

        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.get("/api/user-management/find/{userName}","JackBauer@CTU.com"))
                                        .andExpect(MockMvcResultMatchers.status().isOk())
                                        .andDo(MockMvcResultHandlers.print())
                                        .andReturn();
        assertEquals(result.getResponse().getContentAsString(), userController.findOneUser("JackBauer@CTU.com").toString());

    }
}
