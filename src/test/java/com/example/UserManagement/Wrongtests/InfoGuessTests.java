//package com.example.UserManagement.Wrongtests;
//
//import com.example.UserManagement.entity.User;
//import com.example.UserManagement.function.GuessResEntity;
//import com.example.UserManagement.function.InfoGuess;
//import org.junit.Ignore;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.MockitoAnnotations;
//import org.mockito.Spy;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.web.client.RestTemplate;
//import java.util.List;
//
//
//import static java.util.Arrays.asList;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//@SpringBootTest
//public class InfoGuessTests {
//
//    List<String> NAME = asList("Jane", "Michael", "123456", "#$%$^&*", "ocean", "tifa", "Mare", "Kid","","    ",null);
//
//    @Spy
//    RestTemplate restTemplate;
//
//    @InjectMocks
//    private InfoGuess infoGuess;
//
//    @Spy
//    private GuessResEntity guessResEntity;
//
//    public InfoGuessTests() {
//    }
//
//    @BeforeEach
//    public void setupMock() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Ignore
//    @Test
//    public void guessInfoRight() {
//        String firstname;
//
//        for (int i = 0; i < NAME.size(); i++) {
//
//            firstname = NAME.get(i);
//
//            try {
//                User user = infoGuess.getUserInfoGuess(firstname);
//                guessResEntity.setAge(restTemplate.getForObject("https://api.agify.io/?name={?}", GuessResEntity.class,firstname).getAge());
//                guessResEntity.setGender(restTemplate.getForObject("https://api.genderize.io?name={name}", GuessResEntity.class, firstname).getGender());
//                guessResEntity.setCountry(restTemplate.getForObject("https://api.nationalize.io?name={name}", GuessResEntity.class, firstname).getCountry());
//                assertEquals(guessResEntity.getAge(),user.getAge());
//                assertEquals(guessResEntity.getGender(),user.getGender());
//                assertEquals((guessResEntity.getCountry().isEmpty()) ? null : (String)guessResEntity.getCountry().get(0).get("country_id"),user.getNationality());
//
//                System.out.println("Test " + i + "-- name = " + firstname + " is OK with age = " + user.getAge() + "; gender = " + user.getGender() + "; + nationality = " + user.getNationality());
//            } catch (Exception e) {
//                System.out.println("Test" + i + "-- name = " + firstname + " is NOT OK.");
//                System.out.println("Because " + e.getMessage());
//            }
//        }
//    }
//}


