package com.example.UserManagement.service;

import com.example.UserManagement.entity.User;
import com.example.UserManagement.entity.UserInfoPush;

public interface AddUserService {
    public User getUserInfoFull(UserInfoPush userInfoPush);
}
