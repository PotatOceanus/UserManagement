package com.example.UserManagement;

import com.example.UserManagement.entity.UserInfoPush;
import com.example.UserManagement.entity.UserInfoUpdate;
import com.example.UserManagement.repository.UserRepository;
import com.example.UserManagement.service.impl.AddUserServiceImpl;
import com.example.UserManagement.service.impl.UpdateUserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

public class UserControllerTestsMock {

    @Mock
    private UserRepository userRepository;

    @Mock
    private AddUserServiceImpl addUserServiceImpl;

    @Mock
    private UpdateUserServiceImpl updateUserServiceImpl;

    private UserInfoPush userInfoPush;

    private UserInfoUpdate userInfoUpdate;

    @BeforeEach
    public void setupMock() {
        MockitoAnnotations.openMocks(this);
        userInfoPush = new UserInfoPush();
        userInfoUpdate = new UserInfoUpdate();
    }

    @Test
    public void addOneUserNormal () {

    }
}
