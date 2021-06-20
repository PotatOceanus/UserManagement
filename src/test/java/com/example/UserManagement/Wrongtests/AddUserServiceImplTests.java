//package com.example.UserManagement.Wrongtests;
//
//import com.example.UserManagement.entity.User;
//import com.example.UserManagement.entity.UserInfoPush;
//import com.example.UserManagement.function.GuessResEntity;
//import com.example.UserManagement.function.InfoGen;
//import com.example.UserManagement.function.InfoGuess;
//import com.example.UserManagement.repository.UserRepository;
//import com.example.UserManagement.service.impl.AddUserServiceImpl;
//import org.junit.Ignore;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.mockito.Spy;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.web.client.RestTemplate;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static java.util.Arrays.asList;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//
//@SpringBootTest
//public class AddUserServiceImplTests {
//
//    List<String> PASSWORD = asList("password1","123456","Calpas","$%%^&*",null,"   ","");
//    List<String> NAME = asList("Jane","Michael","123456","#$%$^&*","ocean","tifa","Mare","Kid",null,"  ","");
//    List<String> EMAIL = asList("EMAIL1@TEST.COM","email2@test.com","email3","#$%$^&*@...com",null,"    ","");
//    List<String> CONTACTNUMBER = asList("EMAIL1@TEST.COM","email2@test.com","email3","#$%$^&*@...com","     ",null,"");
//    List<String> TAGS = asList("Shanghai","Guangzhou","SHENZHEN","pk","     ",null,"");
//
//    @Spy
//    RestTemplate restTemplate;
//
//    @Spy
//    private GuessResEntity guessResEntity;
//
//    @Mock
//    private UserInfoPush userInfoPush;
//
//    @Spy
//    @InjectMocks
//    private InfoGen infoGen;
//
//    @Spy
//    @InjectMocks
//    private InfoGuess infoGuess;
//
//    @InjectMocks
//    private AddUserServiceImpl addUserServiceImpl;
//
//    @BeforeEach
//    public void setupMock() {
//        MockitoAnnotations.openMocks(this);
//        userInfoPush = new UserInfoPush();
//    }
//
//    @Ignore
//    @Test
//    public void gatherUserInfoRight () {
//        int index;
//        for (int i = 0; i <= 30; i++) {
//            userInfoPush.setPassword(PASSWORD.get((int) (Math.random() * PASSWORD.size())));
//            userInfoPush.setFirstName(NAME.get((int) (Math.random() * NAME.size())));
//            userInfoPush.setLastName(NAME.get((int) (Math.random() * NAME.size())));
//            userInfoPush.setEmail(EMAIL.get((int) (Math.random() * EMAIL.size())));
//            userInfoPush.setContactNumber(CONTACTNUMBER.get((int) (Math.random() * CONTACTNUMBER.size())));
//            List<String> tags = new ArrayList<String>();
//            for (int j = 0; j <= (int) (Math.random() * TAGS.size()); j++) {
//                tags.add(TAGS.get((int) (Math.random() * TAGS.size())));
//            }
//            userInfoPush.setTags(tags);
//
//            try {
//                User user = addUserServiceImpl.getUserInfoFull(userInfoPush);
//                assertEquals(infoGen.getUserInfoGen(userInfoPush).getUsername(), user.getUsername());
//                assertEquals(userInfoPush.getPassword(), user.getPassword());
//                assertEquals(userInfoPush.getFirstName(), user.getFirstName());
//                assertEquals(userInfoPush.getLastName(), user.getLastName());
//                assertEquals(userInfoPush.getEmail(), user.getEmail());
//                assertEquals(userInfoPush.getContactNumber(), user.getContactNumber());
//                assertEquals(infoGuess.getUserInfoGuess(userInfoPush.getFirstName()).getAge(), user.getAge());
//                assertEquals(infoGuess.getUserInfoGuess(userInfoPush.getFirstName()).getGender(), user.getGender());
//                assertEquals(infoGuess.getUserInfoGuess(userInfoPush.getFirstName()).getNationality(), user.getNationality());
//                assertEquals(infoGen.getUserInfoGen(userInfoPush).getTags(), user.getTags());
//                assertEquals(infoGen.getUserInfoGen(userInfoPush).getStatus(), user.getStatus());
//
//                System.out.println("Test " + i + " is OK -- " + user.toString());
//            } catch (Exception e) {
//                System.out.println("Test " + i + " is NOT OK -- " + userInfoPush);
//                System.out.println(e.getMessage());
//            }
//        }
//    }
//}
