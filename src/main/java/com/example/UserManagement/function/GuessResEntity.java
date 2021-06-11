package com.example.UserManagement.function;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Getter
@Setter
@Service
public class GuessResEntity {

    private String name;
    private int count;
    private int age;
    private String gender;
    private List<HashMap> country;
}
