package com.example.demo.service;

import com.example.demo.dto.StudentAdminViewDto;
import com.example.demo.dto.StudentCreationRequest;
import com.example.demo.dto.StudentUpdateByAdminDto;
import com.example.demo.entity.Password;
import com.example.demo.entity.Role;
import com.example.demo.entity.Student;

import com.example.demo.mapper.StudentMapper;
import com.example.demo.repositories.StudentRepository;
import com.example.demo.repositories.UserRepository;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service

public class AdminService {

    private final StudentRepository studentRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    private final StudentMapper studentMapper;


    public AdminService(StudentRepository studentRepository, UserRepository userRepository, PasswordEncoder passwordEncoder, StudentMapper studentMapper ) {
        this.studentRepository = studentRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.studentMapper = studentMapper;

    }


    public StudentAdminViewDto createStudent(StudentCreationRequest request){
        if(userRepository.findByUsername(request.getUsername()).isPresent()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Пользователь с username");
        }
        Student studentToSave = studentMapper.toEntity(request);
        String encodePassword = passwordEncoder.encode(request.getPassword());
        studentToSave.getUser().setPassword(new Password(encodePassword));
        studentToSave.getUser().setRole(Role.STUDENT);
        studentToSave.getUser().setEnable(true);

        Student savedStudent = studentRepository.save(studentToSave);
        return studentMapper.toAdminViewDto(savedStudent);
    }
    public List<StudentAdminViewDto> getAllStudent(){
        Iterable<Student> students = studentRepository.findAll();
        List<StudentAdminViewDto> resultList = new ArrayList<>();

        for(Student student : students){
            StudentAdminViewDto dto = studentMapper.toAdminViewDto(student);

            resultList.add(dto);
        }
        return resultList;

    }
    public StudentAdminViewDto getStudentById(Long studentId){
        Student student = studentRepository.findById(studentId).orElseThrow(() -> new ResourceNotFoundException("Студент с id: " + studentId + " не найден"));

        return studentMapper.toAdminViewDto(student);
    }

    public StudentAdminViewDto updateStudent(Long studentId, StudentUpdateByAdminDto dto){
        Student studentToUpdate = studentRepository.findById(studentId).orElseThrow(() -> new ResourceNotFoundException(" "));


        studentMapper.updateFromDto(dto, studentToUpdate);
        Student updatedStudent = studentRepository.save(studentToUpdate);
        return studentMapper.toAdminViewDto(updatedStudent);
    }

    public void  deleteStudent(Long studentId){
        if(!studentRepository.existsById(studentId) ){
            throw new ResourceNotFoundException("Студент с id: " + studentId + " ");
        }
        studentRepository.deleteById(studentId);

    }

}
