package com.example.UserManagement;

import com.example.UserManagement.entity.User;
import com.example.UserManagement.entity.UserInfoPush;
import com.example.UserManagement.function.GuessResEntity;
import com.example.UserManagement.function.InfoGen;
import com.example.UserManagement.function.InfoGuess;
import com.example.UserManagement.repository.UserRepository;
import com.example.UserManagement.service.impl.AddUserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;

public class AddUserServiceImplTestsMock {

    @Mock
    InfoGen infogen;

    @Mock
    InfoGuess infoGuess;

    @Mock
    private UserInfoPush userInfoPush;


    @InjectMocks
    private AddUserServiceImpl addUserServiceImpl;

    @BeforeEach
    public void setupMock() {
        MockitoAnnotations.openMocks(this);
        userInfoPush = new UserInfoPush();
    }

    @Test
    public void gatherUserInfoRight () {
        User user_gen = new User();
        User user_guess = new User();

        userInfoPush.setPassword("123456");
        userInfoPush.setFirstName("Jack");
        userInfoPush.setLastName("Bauer");
        userInfoPush.setEmail("JackBauer@CTU.com");
        userInfoPush.setContactNumber("654321");
        List tag= Arrays.asList("CTU","US","Head");
        userInfoPush.setTags(tag);

        user_gen.setUsername("JackBauer@CTU.com");
        user_gen.setTags("CTR:US:Head");
        user_gen.setStatus("active");
        doReturn(user_gen).when(infogen).getUserInfoGen(userInfoPush);

        user_guess.setAge(30);
        user_guess.setGender("male");
        user_guess.setNationality("US");
        doReturn(user_guess).when(infoGuess).getUserInfoGuess(userInfoPush.getFirstName());

        User user = addUserServiceImpl.getUserInfoFull(userInfoPush);

        assertEquals("JackBauer@CTU.com",user.getUsername());
        assertEquals("123456",user.getPassword());
        assertEquals("Jack",user.getFirstName());
        assertEquals("Bauer",user.getLastName());
        assertEquals("JackBauer@CTU.com",user.getEmail());
        assertEquals("654321",user.getContactNumber());
        assertEquals(30,user.getAge());
        assertEquals("male",user.getGender());
        assertEquals("US",user.getNationality());
        assertEquals("CTR:US:Head",user.getTags());
        assertEquals("active",user.getStatus());

    }
}