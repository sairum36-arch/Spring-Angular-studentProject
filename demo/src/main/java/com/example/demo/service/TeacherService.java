package com.example.demo.service;

import com.example.demo.dto.StudentTeacherViewDto;
import com.example.demo.dto.StudentUpdateByTeacherDto;
import com.example.demo.dto.TeacherProfileViewDto;
import com.example.demo.entity.Student;
import com.example.demo.entity.Teacher;
import com.example.demo.entity.User;
import com.example.demo.mapper.StudentCreationMapper;
import com.example.demo.mapper.StudentUpdateMapper;
import com.example.demo.mapper.StudentViewMapper;
import com.example.demo.mapper.TeacherMapper;
import com.example.demo.repositories.StudentRepository;
import com.example.demo.repositories.TeacherRepository;
import com.example.demo.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
@Service

public class TeacherService {
    private final StudentRepository studentRepository;
    private final UserRepository userRepository;



    private final TeacherMapper teacherMapper;
    private final StudentUpdateMapper studentUpdateMapper;
    private final StudentViewMapper studentViewMapper;
    private final TeacherRepository teacherRepository;

    public TeacherService(StudentRepository studentRepository, UserRepository userRepository,  StudentUpdateMapper studentUpdateMapper, StudentViewMapper studentViewMapper, TeacherRepository teacherRepository, TeacherMapper teacherMapper) {
        this.studentRepository = studentRepository;
        this.userRepository = userRepository;
        this.teacherMapper = teacherMapper;
        this.studentUpdateMapper = studentUpdateMapper;
        this.studentViewMapper = studentViewMapper;
        this.teacherRepository = teacherRepository;
    }

    public TeacherProfileViewDto getMyProfile(String username) {
        Teacher teacher = findTeacherByUsername(username);
        return teacherMapper.toProfileViewDto(teacher);
    }

    public List<StudentTeacherViewDto> getStudentsByGroup(String group, String teacherUsername) {
        Teacher teacher = findTeacherByUsername(teacherUsername);


        if (!teacher.getGroup().equals(group)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Вы не можете просматривать студентов из другой группы.");
        }

        List<Student> students = studentRepository.findByGroup(group);

        List<StudentTeacherViewDto> dtos = new ArrayList<>();
        for (Student student : students) {
            dtos.add(studentViewMapper.toTeacherViewDto(student));
        }
        return dtos;
    }
    public StudentTeacherViewDto updateStudentByTeacher(Long studentId, StudentUpdateByTeacherDto dto, String teacherUsername) {
        Teacher teacher = findTeacherByUsername(teacherUsername);
        Student studentToUpdate = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Студент с id: " + studentId + " не найден"));


        if (!studentToUpdate.getGroup().equals(teacher.getGroup())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Вы можете редактировать только студентов своей группы.");
        }

        studentUpdateMapper.updateFromDto(dto, studentToUpdate);
        Student updatedStudent = studentRepository.save(studentToUpdate);
        return studentViewMapper.toTeacherViewDto(updatedStudent);
    }

    private Teacher findTeacherByUsername(String username){
        User user = userRepository.findByUsername(username).orElseThrow(()-> new UsernameNotFoundException("пользователь с логином " + username + "не найден"));

        return teacherRepository.findByUser(user).orElseThrow(()-> new ResourceNotFoundException("профиль учителя " + username + "не найден"));
    }



}
