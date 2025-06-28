package com.example.demo.dto;

import lombok.Setter;
import lombok.Getter;


@Getter
@Setter
public class StudentCreationRequest {
    private String fio;
    private String group;
    private String phoneNumber;

    private String username;
    private String password;
}
