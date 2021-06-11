package com.example.UserManagement.function;

import com.example.UserManagement.entity.User;
import com.example.UserManagement.entity.UserInfoPush;
//import com.example.demo.service.impl.AddUserServiceImpl;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.stream.Collectors;

@Service
public class InfoGen {

//    time, ISO date format - current datetime in UTC
    public static String getStringDate() {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        df.setTimeZone(TimeZone.getTimeZone("UTC"));
        String timestamp = df.format(new Date());
        return timestamp;
    }

    public User getUserInfoGen (UserInfoPush userInfoPush) {

        User user = new User();

//        set username
        String username = userInfoPush.getEmail();
        user.setUsername(username);

//        set tags
        String tags = userInfoPush.getTags().stream().collect(Collectors.joining(":"));
        user.setTags(tags);

//        set status
        user.setStatus("active");

//        seg created time and updated time
        user.setCreated(getStringDate());
        user.setUpdated(getStringDate());

        return user;
    }

}
