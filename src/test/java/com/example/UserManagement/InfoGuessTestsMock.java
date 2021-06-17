package com.example.UserManagement;

import com.example.UserManagement.entity.User;
import com.example.UserManagement.function.GuessResEntity;
import com.example.UserManagement.function.InfoGuess;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;

public class InfoGuessTestsMock {

    @Mock
    RestTemplate restTemplate;

    @Mock
    private GuessResEntity guessResEntity;

    @InjectMocks
    private InfoGuess infoGuess;

    public InfoGuessTestsMock() {
    }

    @BeforeEach
    public void setupMock() {
        MockitoAnnotations.openMocks(this);
//        restTemplate = new RestTemplate();
//        guessResEntity = new GuessResEntity();
    }

    @Test
    public void guessInfoRight() {
        String firstname = "Jack";

        List<HashMap> country = new ArrayList<>();
        HashMap map = new HashMap() ;
        map.put("probability",0.07);
        map.put("country_id","US");
        country.add(map);
        guessResEntity = new GuessResEntity();
        guessResEntity.setAge(30);
        guessResEntity.setGender("male");
        guessResEntity.setCountry(country);

        doReturn(guessResEntity).when(restTemplate).getForObject("https://api.agify.io/?name={?}", GuessResEntity.class,firstname);
        doReturn(guessResEntity).when(restTemplate).getForObject("https://api.genderize.io?name={name}", GuessResEntity.class,firstname);
        doReturn(guessResEntity).when(restTemplate).getForObject("https://api.nationalize.io?name={name}", GuessResEntity.class,firstname);
        User user = infoGuess.getUserInfoGuess(firstname);

        assertEquals(30,user.getAge());
        assertEquals("male",user.getGender());
        assertEquals("US",user.getNationality());

        }
    }

