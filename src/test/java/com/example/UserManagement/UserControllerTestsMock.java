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

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.*;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.util.NestedServletException;


import java.util.*;

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

    private UserNotFoundException userNotFoundException;

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


    // add user
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
        verify(userRepository, times(1)).save(user1);
    }


    // find one user
    @Test
    public void findOneUserNormal () throws Exception {
        //given
        Mockito.when(userRepository.findById("JackBauer@CTU.com")).thenReturn(Optional.of(user1));
        //when
        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.get("/api/user-management/find/{userName}","JackBauer@CTU.com"))
                                        .andExpect(MockMvcResultMatchers.status().isOk())
                                        .andReturn();
        //then
        assertEquals(result.getResponse().getContentAsString(), userController.findOneUser("JackBauer@CTU.com").toString());
    }

    @Test
    public void findOneUserNotFound () throws Exception {

        Mockito.when(userRepository.findById("JackBau@CTU.com")).thenReturn(Optional.empty());

        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.get("/api/user-management/find/{userName}","JackBau@CTU.com"))
                .andDo(print())
                .andReturn();

        assertEquals(404, result.getResponse().getStatus());
        assertEquals("User not found by this username : " + "{" + "JackBau@CTU.com" + "}", result.getResolvedException().getMessage());
    }

    @Test
    public void findOneUserRuntimeException () throws Exception {

        Mockito.when(userRepository.findById("JackBauer@CTU.com")).thenThrow(new RuntimeException("Timeout"));

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/user-management/find/{userName}","JackBauer@CTU.com");

        Assertions.assertThrows(NestedServletException.class, () -> { mockMvc.perform(requestBuilder).andReturn(); });
    }


    //find all user
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


    // delete one user
    @Test
    public void deleteOneUserNormal () throws Exception {

        Mockito.when(userRepository.findById("JackBauer@CTU.com")).thenReturn(java.util.Optional.of(user1));

        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.get("/api/user-management/delete/{userName}","JackBauer@CTU.com"))
                .andDo(print())
                .andReturn();

        assertEquals(200,result.getResponse().getStatus());
        verify(userRepository, times(1)).deleteById("JackBauer@CTU.com");
    }

    @Test
    public void deleteOneUserNotFound () throws Exception {

        Mockito.when(userRepository.findById("JackBau@CTU.com")).thenReturn(Optional.empty());

        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.get("/api/user-management/delete/{userName}","JackBau@CTU.com"))
                .andDo(print())
                .andReturn();

        assertEquals(404, result.getResponse().getStatus());
        assertEquals("User(to delete) not found by this username : " + "{" + "JackBau@CTU.com" + "}", result.getResolvedException().getMessage());
    }


    //update one user
    @Test
    public void updateOneUserNormal () throws Exception {
        userInfoUpdate.setUsername("JackBauer@CTU.com");
        userInfoUpdate.setFirstName("Kate");
        userInfoUpdate.setAge(32);
        userInfoUpdate.setEmail("KateBauer@CTU.com");

        User user3 = new User("JackBauer@CTU.com",
                "123456",
                "Kate",
                "Bauer",
                "KateBauer@CTU.com",
                "654321",
                32,
                "female",
                "AU",
                "CTU:AU:Rose",
                "active",
                "2021-06-16T22:10:36.858Z",
                "2021-06-18T22:10:36.858Z");

        Mockito.when(userRepository.findById("JackBauer@CTU.com")).thenReturn(java.util.Optional.of(user1));
        doReturn(user3).when(updateUserServiceImpl).makeUserInfoFull(any(UserInfoUpdate.class));;

        MvcResult result = mockMvc.perform(post("/api/user-management/update")
                .content(JSON.toJSONString(userInfoUpdate))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andReturn();

        verify((userRepository),times(1)).findById("JackBauer@CTU.com");
        verify((userRepository),times(1)).save(user3);
    }

    @Test
    public void updateOneUserNotFound () throws Exception {
        userInfoUpdate.setUsername("Jack@CTU.com");
        userInfoUpdate.setFirstName("Kate");
        userInfoUpdate.setAge(32);
        userInfoUpdate.setEmail("KateBauer@CTU.com");

        Mockito.when(userRepository.findById("Jack@CTU.com")).thenReturn(Optional.empty());

        MvcResult result = mockMvc.perform(post("/api/user-management/update")
                .content(JSON.toJSONString(userInfoUpdate))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andReturn();

        assertEquals(404, result.getResponse().getStatus());
        assertEquals("User(to update) not found by this username : " + "{" + "Jack@CTU.com" + "}", result.getResolvedException().getMessage());
    }

}