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

    //TODO: почитать о необходимости response entity (сделано)
    @GetMapping("/profile/me")
    public TeacherProfileViewDto getMyTeacherProfile(Principal principal) {
        TeacherProfileViewDto profile = teacherService.getMyProfile(principal.getName());
        return profile;
    }

    @GetMapping("/students/by-group/{group}")
    public List<StudentTeacherViewDto> getStudentsByGroup(@PathVariable String group, Principal principal) {
        List<StudentTeacherViewDto> students = teacherService.getStudentsByGroup(group, principal.getName());
        return students;
    }
    @PutMapping("/students/{studentId}")
    public StudentTeacherViewDto updateStudentByTeacher(@PathVariable Long studentId,
                                                                        @RequestBody StudentUpdateByTeacherDto dto,
                                                                        Principal principal) {
        StudentTeacherViewDto updatedStudent = teacherService.updateStudentByTeacher(studentId, dto, principal.getName());
        return updatedStudent;
    }
}
