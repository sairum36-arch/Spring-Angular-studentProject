package com.example.demo.controllers;

import com.example.demo.dto.MyProfileDto;
import com.example.demo.dto.MyProfileUpdateDto;
import com.example.demo.service.StudentProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/student")
@RequiredArgsConstructor

public class StudentProfileController {
    private final StudentProfileService studentProfileService;


    @GetMapping("/profile")
    public ResponseEntity<MyProfileDto> getMyProfile(Principal principal) {
        MyProfileDto profile = studentProfileService.getMyProfile(principal.getName());
        return ResponseEntity.ok(profile);
    }

    @PutMapping("/profile")
    public ResponseEntity<MyProfileDto> updateMyProfile(Principal principal, @RequestBody MyProfileUpdateDto dto) {
        MyProfileDto updatedProfile = studentProfileService.updateMyProfile(principal.getName(), dto);
        return ResponseEntity.ok(updatedProfile);
    }
}
