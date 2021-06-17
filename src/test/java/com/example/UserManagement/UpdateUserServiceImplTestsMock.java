package com.example.UserManagement;

import com.example.UserManagement.entity.User;
import com.example.UserManagement.entity.UserInfoUpdate;
import com.example.UserManagement.exceptions.UserNotFoundException;
import com.example.UserManagement.function.InfoGuess;
import com.example.UserManagement.repository.UserRepository;
import com.example.UserManagement.service.impl.UpdateUserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.*;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;


public class UpdateUserServiceImplTestsMock {

    List<String> USERNAME = asList("11111@1111.com","12634@905054.com","127@potato.com","aaa@bcd.com","popo@potato.com");
    List<String> PASSWORD = asList("password1","123456","Calpas","$%%^&*",null,"   ","");
    List<String> NAME = asList("Jane","Michael","123456","#$%$^&*","ocean","tifa","Mare","Kid",null,"  ","");
    List<String> EMAIL = asList("EMAIL1@TEST.COM","email2@test.com","email3","#$%$^&*@...com","computer@dot.com",null,"    ","");
    List<String> CONTACTNUMBER = asList("123456","37684555","42310056778","#$%$^&*596850","2495143269","     ",null,"");
    List<Integer> AGE = asList(1,10,30,60,1000);
    List<String> GENDER = asList("Male","Female",null);
    List<String> NATIONALITY = asList("US","CN","IR","AU","JP",null);
    List<String> TAGS = asList("Shanghai","Guangzhou","SHENZHEN","pk","weinne","     ",null,"");
    List<String> STATUS = asList("active");

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
    public void makeUserInfoRight () throws UserNotFoundException {
        userInfoUpdate.setUsername("JackBauer@CTU.com");
        userInfoUpdate.setPassword("234567");
        userInfoUpdate.setFirstName("Bauer");
        userInfoUpdate.setLastName("Jack");
        userInfoUpdate.setContactNumber("765432");
        List tag = Arrays.asList("CTU","US","Head","Retired");
        userInfoUpdate.setTags(tag);

        User user_to_update = new User();
        User user_guess = new User();
        User user = new User();

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

        user_guess.setAge(25);
        user_guess.setGender("female");
        user_guess.setNationality("TW");
        doReturn(user_guess).when(infoGuess).getUserInfoGuess(userInfoUpdate.getFirstName());

        user = updateUserServiceImpl.makeUserInfoFull(userInfoUpdate);

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
