package com.example.UserManagement.function;

import com.example.UserManagement.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class InfoGuess {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    private GuessResEntity guessResEntity;


    public User getUserInfoGuess (String firstName) {
//        RestTemplate restTemplate = new RestTemplate();

        User user = new User();

//        guess age
        guessResEntity = restTemplate.getForObject("https://api.agify.io/?name={?}", GuessResEntity.class,firstName);
        user.setAge(guessResEntity.getAge());

//        guess gender
        guessResEntity = restTemplate.getForObject("https://api.genderize.io?name={name}", GuessResEntity.class, firstName);
        user.setGender(guessResEntity.getGender());

//        guess nationality
        guessResEntity = restTemplate.getForObject("https://api.nationalize.io?name={name}", GuessResEntity.class, firstName);
        if (!guessResEntity.getCountry().isEmpty()) {
            user.setNationality((String)guessResEntity.getCountry().get(0).get("country_id"));
        } else {
            user.setNationality(null);
        }

        return user;
    }
}
