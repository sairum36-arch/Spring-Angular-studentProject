package com.example.demo.dto;
import lombok.Getter;
import lombok.AllArgsConstructor;

@Getter
@AllArgsConstructor
public class UserDto {
    private Long id;
    private String username;
    private String role;
}
