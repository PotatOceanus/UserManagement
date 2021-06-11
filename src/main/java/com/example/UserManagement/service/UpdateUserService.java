package com.example.UserManagement.service;

import com.example.UserManagement.entity.User;
import com.example.UserManagement.entity.UserInfoUpdate;
import com.example.UserManagement.exceptions.UserNotFoundException;

public interface UpdateUserService {
    public User makeUserInfoFull(UserInfoUpdate userInfoUpdate) throws UserNotFoundException;
}
