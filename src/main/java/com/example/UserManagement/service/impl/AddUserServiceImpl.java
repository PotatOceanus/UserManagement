package com.example.UserManagement.service.impl;

import com.example.UserManagement.entity.User;
import com.example.UserManagement.entity.UserInfoPush;
import com.example.UserManagement.function.InfoGen;
import com.example.UserManagement.function.InfoGuess;
import com.example.UserManagement.repository.UserRepository;
import com.example.UserManagement.service.AddUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddUserServiceImpl implements AddUserService {

    @Autowired
    private InfoGen infoGen;

    @Autowired
    private InfoGuess infoGuess;

    @Override
    public User getUserInfoFull(UserInfoPush userInfoPush) {

        User user_gen = infoGen.getUserInfoGen(userInfoPush);
        User user_guess = infoGuess.getUserInfoGuess(userInfoPush.getFirstName());

        User user = new User(user_gen.getUsername(),
                            userInfoPush.getPassword(),
                            userInfoPush.getFirstName(),
                            userInfoPush.getLastName(),
                            userInfoPush.getEmail(),
                            userInfoPush.getContactNumber(),
                            user_guess.getAge(),
                            user_guess.getGender(),
                            user_guess.getNationality(),
                            user_gen.getTags(),
                            user_gen.getStatus(),
                            user_gen.getCreated(),
                            user_gen.getUpdated());

        return user;
    }
}
