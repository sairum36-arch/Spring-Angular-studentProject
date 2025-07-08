package com.example.demo.service;

import com.example.demo.dto.MyProfileDto;
import com.example.demo.dto.MyProfileUpdateDto;
import com.example.demo.entity.Student;
import com.example.demo.entity.User;
import com.example.demo.mapper.StudentMapper;
import com.example.demo.repositories.StudentRepository;
import com.example.demo.repositories.UserRepository;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service

public class StudentProfileService {

    private final StudentRepository studentRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    private final StudentMapper studentMapper;


    public StudentProfileService(StudentRepository studentRepository, UserRepository userRepository, PasswordEncoder passwordEncoder, StudentMapper studentMapper) {
        this.studentRepository = studentRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.studentMapper = studentMapper;

    }
    public MyProfileDto getMyProfile(String username){
        Student student = findStudentByUsername(username);

        return studentMapper.toMyProfileDto(student);
    }

    public MyProfileDto updateMyProfile(String username, MyProfileUpdateDto dto){
        Student studentToUpdate = findStudentByUsername(username);
        studentMapper.updateFromDto(dto, studentToUpdate);
        Student updatedStudent = studentRepository.save(studentToUpdate);
        return studentMapper.toMyProfileDto(updatedStudent);

    }

    private Student findStudentByUsername(String username ){
        User user = userRepository.findByUsername(username).orElseThrow(()-> new UsernameNotFoundException("пользователь с логином " + username + " не найден"));
        return studentRepository.findByUser(user).orElseThrow(()-> new ResourceNotFoundException("Профиль студента для пользователя" + username + "не найден"));
    }
}
