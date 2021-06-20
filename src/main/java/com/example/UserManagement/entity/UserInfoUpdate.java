package com.example.UserManagement.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Service
public class UserInfoUpdate {

    @Id
    @NotBlank(message = "Blank username forbidden")
    private String username;

    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String contactNumber;
    private int age;
    private String gender;
    private String nationality;
    private List<String> tags;
    private String status;
    @EqualsAndHashCode.Exclude private String updated;

    public String getTags() {
        return (tags != null) ? tags.stream().collect(Collectors.joining(":")) : null;
        }

    @Override
    public String toString() {
        String tags_print = "";
        for (int i = 0;i < tags.size() - 1; i++) {
            tags_print = tags_print + "\"" + tags.get(i) + "\",";
        }
        tags_print = tags_print + "\"" + tags.get(tags.size() - 1) + "\"";

        return "{"
                + "\"username\"" + ":\"" + username + "\","
                + "\"password\"" + ":\"" +password + "\","
                + "\"firstName\"" + ":\"" +firstName + "\","
                + "\"lastName\"" + ":\"" +lastName + "\","
                + "\"email\"" + ":\"" +email + "\","
                + "\"contactNumber\"" + ":\"" + contactNumber + "\","
                + "\"age\"" + ":" + age + ","
                + "\"gender\"" + ":\"" + gender + "\","
                + "\"nationality\"" + ":\"" + nationality + "\","
                + "\"tags\"" + ":[" + tags_print + "]"
                + "\"updated\"" + ":\"" + updated + "\""
                + "}";
    }
    }
