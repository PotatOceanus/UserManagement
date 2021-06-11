package com.example.UserManagement.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="app_user")
public class User {


    @Id
    private String username;

    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String contactNumber;
    private int age;
    private String gender;
    private String nationality;
    private String tags;
    private String status;
    @EqualsAndHashCode.Exclude private String created;
    @EqualsAndHashCode.Exclude private String updated;

}
