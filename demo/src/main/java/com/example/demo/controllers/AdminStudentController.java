package com.example.demo.controllers;


import com.example.demo.dto.StudentAdminViewDto;
import com.example.demo.dto.StudentCreationRequest;
import com.example.demo.dto.StudentUpdateByAdminDto;
import com.example.demo.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor


public class AdminStudentController {
    private final AdminService adminService;

    @PostMapping
    public ResponseEntity<StudentAdminViewDto> createStudent(@RequestBody StudentCreationRequest request){
        StudentAdminViewDto createdStudent = adminService.createStudent(request);
        return new ResponseEntity<>(createdStudent, HttpStatus.CREATED);

    }

    @GetMapping("/students")
    public List<StudentAdminViewDto> getAllStudents(){
        return adminService.getAllStudent();
    }

    @GetMapping("/students/{id}")
    public ResponseEntity<StudentAdminViewDto> getStudentById(@PathVariable Long id){
        StudentAdminViewDto student = adminService.getStudentById(id);
        return ResponseEntity.ok(student);
    }
    @PutMapping("/students/{id}")
    public ResponseEntity<StudentAdminViewDto> updateStudent(@PathVariable Long id, @RequestBody StudentUpdateByAdminDto dto){
        StudentAdminViewDto updatedStudent = adminService.updateStudent(id, dto);
        return ResponseEntity.ok(updatedStudent);
    }
    @DeleteMapping("/students/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id){
        adminService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }
}
