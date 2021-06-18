package com.example.UserManagement;

import com.example.UserManagement.entity.User;
import com.example.UserManagement.entity.UserInfoUpdate;
import com.example.UserManagement.function.InfoGuess;
import com.example.UserManagement.repository.UserRepository;
import com.example.UserManagement.service.impl.UpdateUserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.*;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;



public class UpdateUserServiceImplTestsMock {

    @Mock
    UserRepository userRepository;

    @Mock
    InfoGuess infoGuess;

    private UserInfoUpdate userInfoUpdate;

    @InjectMocks
    private UpdateUserServiceImpl updateUserServiceImpl;

    @BeforeEach
    public void setupMock() {
        MockitoAnnotations.openMocks(this);
        userInfoUpdate = new UserInfoUpdate();
    }

    @Test
    public void makeUserInfoRight () throws Exception {
        userInfoUpdate.setUsername("JackBauer@CTU.com");
        userInfoUpdate.setPassword("234567");
        userInfoUpdate.setFirstName("Bauer");
        userInfoUpdate.setLastName("Jack");
        userInfoUpdate.setContactNumber("765432");
        List tag = Arrays.asList("CTU","US","Head","Retired");
        userInfoUpdate.setTags(tag);

        User user_to_update = new User();
        user_to_update.setUsername("JackBauer@CTU.com");
        user_to_update.setPassword("123456");
        user_to_update.setFirstName("Jack");
        user_to_update.setLastName("Bauer");
        user_to_update.setEmail("JackBauer@CTU.com");
        user_to_update.setContactNumber("654321");
        user_to_update.setTags("CTU:US:Head");
        user_to_update.setAge(30);
        user_to_update.setGender("male");
        user_to_update.setNationality("US");
        user_to_update.setStatus("active");
        doReturn(Optional.of(user_to_update)).when(userRepository).findById(userInfoUpdate.getUsername());

        User user_guess = new User();
        user_guess.setAge(25);
        user_guess.setGender("female");
        user_guess.setNationality("TW");
        doReturn(user_guess).when(infoGuess).getUserInfoGuess(userInfoUpdate.getFirstName());

        User user = updateUserServiceImpl.makeUserInfoFull(userInfoUpdate);

        assertEquals("JackBauer@CTU.com",user.getUsername());
        assertEquals("234567",user.getPassword());
        assertEquals("Bauer",user.getFirstName());
        assertEquals("Jack",user.getLastName());
        assertEquals("JackBauer@CTU.com",user.getEmail());
        assertEquals("765432",user.getContactNumber());
        assertEquals(25,user.getAge());
        assertEquals("female",user.getGender());
        assertEquals("TW",user.getNationality());
        assertEquals("CTU:US:Head:Retired",user.getTags());
        assertEquals("active",user.getStatus());

    }

}