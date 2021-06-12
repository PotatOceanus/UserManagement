package com.example.UserManagement.entity;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;
import java.util.List;

@Getter
@Setter
public class UserInfoPush {
    @NotBlank(message = "Blank password forbidden")
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String contactNumber;
    private List<String> tags;

    @Override
    public String toString() {
        return "User { "
                + "password = " + password + " ; "
                + "firstname = " + firstName + " ; "
                + "lastname = " + lastName + " ; "
                + "email = " + email + " ; "
                + "contactNumber = " + contactNumber + " ; "
                + "tags = " + tags + " ; "
                + " }.";

    }
}