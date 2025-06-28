package com.example.demo.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
@Data
@AllArgsConstructor
public class JwtResponse {
    private final String token;
    private final UserDto user;
}