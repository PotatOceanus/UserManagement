package com.example.UserManagement;

import com.example.UserManagement.entity.User;
import com.example.UserManagement.entity.UserInfoPush;
import com.example.UserManagement.function.InfoGen;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import static java.util.Arrays.asList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InfoGenTestsMock {

    List<String> PASSWORD = asList("password1","123456","Calpas","$%%^&*",null,"   ","");
    List<String> NAME = asList("Jane","Michael","123456","#$%$^&*","ocean","tifa","Mare","Kid",null,"  ","");
    List<String> EMAIL = asList("EMAIL1@TEST.COM","email2@test.com","email3","#$%$^&*@...com",null,"    ","");
    List<String> CONTACTNUMBER = asList("EMAIL1@TEST.COM","email2@test.com","email3","#$%$^&*@...com","     ",null,"");
    List<String> TAGS = asList("Shanghai","Guangzhou","SHENZHEN","pk","     ",null,"");

    @Mock
    private UserInfoPush userInfoPush;

    @InjectMocks
    private InfoGen infoGen;

    @BeforeEach
    public void setupMock() {
        MockitoAnnotations.openMocks(this);
        userInfoPush = new UserInfoPush();
    }

    @Test
    public void generateInfoRight () {
        int index;
        for (int i = 0; i < 30; i++) {
            userInfoPush.setPassword(PASSWORD.get((int) (Math.random() * PASSWORD.size())));
            userInfoPush.setFirstName(NAME.get((int) (Math.random() * NAME.size())));
            userInfoPush.setLastName(NAME.get((int) (Math.random() * NAME.size())));
            userInfoPush.setEmail(EMAIL.get((int) (Math.random() * EMAIL.size())));
            userInfoPush.setContactNumber(CONTACTNUMBER.get((int) (Math.random() * CONTACTNUMBER.size())));
            List<String> tags = new ArrayList<String>();
            for (int j = 0; j <= (int) (Math.random() * TAGS.size()); j++) {
                tags.add(TAGS.get((int) (Math.random() * TAGS.size())));
            }
            userInfoPush.setTags(tags);

            try {
                User user = infoGen.getUserInfoGen(userInfoPush);
                assertEquals(userInfoPush.getEmail(), user.getUsername());
                assertEquals(tags.stream().collect(Collectors.joining(":")), user.getTags());
                assertEquals("active", user.getStatus());
                System.out.println("Test" + i + " is OK. -- " + userInfoPush.toString() );
            } catch(Exception e) {
                System.out.println(e);
                System.out.println("Test:" + i + " is NOT OK. -- " + userInfoPush.toString());
                break;
            }
        }
    }
}
