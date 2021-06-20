//package com.example.UserManagement.Wrongtests;
//
//import com.example.UserManagement.entity.User;
//import com.example.UserManagement.entity.UserInfoPush;
//import com.example.UserManagement.entity.UserInfoUpdate;
//import com.example.UserManagement.exceptions.UserNotFoundException;
//import com.example.UserManagement.function.GuessResEntity;
//import com.example.UserManagement.function.InfoGen;
//import com.example.UserManagement.function.InfoGuess;
//import com.example.UserManagement.repository.UserRepository;
//import com.example.UserManagement.service.impl.AddUserServiceImpl;
//import com.example.UserManagement.service.impl.UpdateUserServiceImpl;
//import org.junit.Ignore;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.mockito.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.SpringBootConfiguration;
//import org.springframework.boot.autoconfigure.domain.EntityScan;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.context.TestConfiguration;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.boot.test.mock.mockito.SpyBean;
//import org.springframework.boot.test.mock.mockito.SpyBeans;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.data.domain.Example;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.Sort;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.stereotype.Repository;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.web.client.RestTemplate;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import static java.util.Arrays.asList;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//@SpringBootTest
//public class UpdateUserServiceImplTests {
//
//    List<String> USERNAME = asList("11111@1111.com","12634@905054.com","127@potato.com","aaa@bcd.com","popo@potato.com");
//    List<String> PASSWORD = asList("password1","123456","Calpas","$%%^&*",null,"   ","");
//    List<String> NAME = asList("Jane","Michael","123456","#$%$^&*","ocean","tifa","Mare","Kid",null,"  ","");
//    List<String> EMAIL = asList("EMAIL1@TEST.COM","email2@test.com","email3","#$%$^&*@...com","computer@dot.com",null,"    ","");
//    List<String> CONTACTNUMBER = asList("123456","37684555","42310056778","#$%$^&*596850","2495143269","     ",null,"");
//    List<Integer> AGE = asList(1,10,30,60,1000);
//    List<String> GENDER = asList("Male","Female",null);
//    List<String> NATIONALITY = asList("US","CN","IR","AU","JP",null);
//    List<String> TAGS = asList("Shanghai","Guangzhou","SHENZHEN","pk","weinne","     ",null,"");
//    List<String> STATUS = asList("active");
//
//    @Captor
//    UserRepository userRepository;
//
//    @Spy
//    RestTemplate restTemplate;
//
//    @Spy
//    private GuessResEntity guessResEntity;
//
//    @Mock
//    private UserInfoUpdate userInfoUpdate;
//
//    @Autowired
//    UserRepository userRepository1;
//
//    @InjectMocks
//    private UpdateUserServiceImpl updateUserServiceImpl;
//
//    @BeforeEach
//    public void setupMock() {
//        MockitoAnnotations.openMocks(this);
//        userInfoUpdate = new UserInfoUpdate();
//    }
//
//    @Ignore
//    @Test
//    public void makeUserInfoRight (){
//        System.out.println(userRepository1);
//        int index;
//        for (int i = 0; i <= 0; i++) {
//            userInfoUpdate.setUsername(USERNAME.get((int) (Math.random() * USERNAME.size())));
//            userInfoUpdate.setPassword(PASSWORD.get((int) (Math.random() * PASSWORD.size())));
//            userInfoUpdate.setFirstName(NAME.get((int) (Math.random() * NAME.size())));
//            userInfoUpdate.setLastName(NAME.get((int) (Math.random() * NAME.size())));
//            userInfoUpdate.setEmail(EMAIL.get((int) (Math.random() * EMAIL.size())));
//            userInfoUpdate.setContactNumber(CONTACTNUMBER.get((int) (Math.random() * CONTACTNUMBER.size())));
//            userInfoUpdate.setAge(AGE.get((int) (Math.random() * AGE.size())));
//            userInfoUpdate.setGender(GENDER.get((int) (Math.random() * GENDER.size())));
//            userInfoUpdate.setNationality(NATIONALITY.get((int) (Math.random() * NATIONALITY.size())));
//            List<String> tags = new ArrayList<String>();
//            for (int j = 0; j <= (int) (Math.random() * TAGS.size()); j++) {
//                tags.add(TAGS.get((int) (Math.random() * TAGS.size())));
//            }
//            userInfoUpdate.setTags(tags);
//
//            try {
//                User user = updateUserServiceImpl.makeUserInfoFull(userInfoUpdate);
//                User user_to_update = userRepository1.findById(userInfoUpdate.getUsername())
//                        .orElseThrow(() -> new UserNotFoundException("User not found by this username : " + "{" + userInfoUpdate.getUsername() + "}"));
//                System.out.println(user_to_update.getUsername());
//
//
////                assertEquals(userInfoUpdate.getUsername(), user.getUsername());
////                assertEquals(userInfoUpdate.getPassword() != null ? user_to_update.getPassword() : user_to_update.getPassword(), user.getPassword());
////                assertEquals(userInfoPush.getFirstName(), user.getFirstName());
////                assertEquals(userInfoPush.getLastName(), user.getLastName());
////                assertEquals(userInfoPush.getEmail(), user.getEmail());
////                assertEquals(userInfoPush.getContactNumber(), user.getContactNumber());
////                assertEquals(infoGuess.getUserInfoGuess(userInfoPush.getFirstName()).getAge(), user.getAge());
////                assertEquals(infoGuess.getUserInfoGuess(userInfoPush.getFirstName()).getGender(), user.getGender());
////                assertEquals(infoGuess.getUserInfoGuess(userInfoPush.getFirstName()).getNationality(), user.getNationality());
////                assertEquals(infoGen.getUserInfoGen(userInfoPush).getTags(), user.getTags());
////                assertEquals(infoGen.getUserInfoGen(userInfoPush).getStatus(), user.getStatus());
//
////                System.out.println("Test " + i + " is OK -- " + user.toString());
//            } catch (Exception e) {
////                System.out.println("Test " + i + " is NOT OK -- " + userInfoUpdate.getUsername());
//                System.out.println(e.getMessage());
//            }
//        }
//    }
//}