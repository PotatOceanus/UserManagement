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
    GuessResEntity guessResEntity;

//    UserInfoGuess userInfoGuess = new UserInfoGuess();

//    public int getAge (String firstName) {
//        Age age = restTemplate.getForObject("https://api.agify.io/?name={nameE}", Age.class, firstName);
//        return age.getAge();
//    }
//
//    public String getGender (String firstName) {
//        Gender gender = restTemplate.getForObject("https://api.genderize.io?name={nameE}", Gender.class, firstName);
//        return gender.getGender();
//    }
//
//    public String getNationality (String firstName) {
//        Nationality nationality = restTemplate.getForObject("https://api.nationalize.io?name={nameE}", Nationality.class, firstName);
//        return (String) nationality.getCountry().get(0).get("country_id");
//    }

    public User getUserInfoGuess (String firstName) {


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
            user.setNationality((String)restTemplate.getForObject("https://api.nationalize.io?name={name}", GuessResEntity.class, firstName).getCountry().get(0).get("country_id"));
        } else {
            user.setNationality(null);
        }

        return user;
    }
}
