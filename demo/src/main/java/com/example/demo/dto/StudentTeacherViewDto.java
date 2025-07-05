package com.example.demo.dto;

import lombok.Setter;
import lombok.Getter;

@Getter
@Setter
public class StudentTeacherViewDto {
    private String fio;
    private String group;
    private String phoneNumber;

    private Long studentId;
}
