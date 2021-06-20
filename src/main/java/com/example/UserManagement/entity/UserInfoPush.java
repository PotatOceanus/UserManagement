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
        String tags_print = "";
        for (int i = 0;i < tags.size() - 1; i++) {
            tags_print = tags_print + "\"" + tags.get(i) + "\",";
        }
        tags_print = tags_print + "\"" + tags.get(tags.size() - 1) + "\"";

        return "{"
                + "\"password\"" + ":\"" +password + "\","
                + "\"firstName\"" + ":\"" +firstName + "\","
                + "\"lastName\"" + ":\"" +lastName + "\","
                + "\"email\"" + ":\"" +email + "\","
                + "\"contactNumber\"" + ":\"" + contactNumber + "\","
                + "\"tags\"" + ":[" + tags_print + "]"
                + "}";
    }
}