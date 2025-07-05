package com.example.demo.controllers;


import com.example.demo.dto.StudentTeacherViewDto;
import com.example.demo.dto.StudentUpdateByTeacherDto;
import com.example.demo.dto.TeacherProfileViewDto;
import com.example.demo.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/teacher")
@RequiredArgsConstructor

public class TeacherStudentController {

    private final TeacherService teacherService;

    @GetMapping("/profile/me")
    public ResponseEntity<TeacherProfileViewDto> getMyTeacherProfile(Principal principal) {
        TeacherProfileViewDto profile = teacherService.getMyProfile(principal.getName());
        return ResponseEntity.ok(profile);
    }

    @GetMapping("/students/by-group/{group}")
    public ResponseEntity<List<StudentTeacherViewDto>> getStudentsByGroup(@PathVariable String group, Principal principal) {
        List<StudentTeacherViewDto> students = teacherService.getStudentsByGroup(group, principal.getName());
        return ResponseEntity.ok(students);
    }
    @PutMapping("/students/{studentId}")
    public ResponseEntity<StudentTeacherViewDto> updateStudentByTeacher(@PathVariable Long studentId,
                                                                        @RequestBody StudentUpdateByTeacherDto dto,
                                                                        Principal principal) {
        StudentTeacherViewDto updatedStudent = teacherService.updateStudentByTeacher(studentId, dto, principal.getName());
        return ResponseEntity.ok(updatedStudent);
    }
}
