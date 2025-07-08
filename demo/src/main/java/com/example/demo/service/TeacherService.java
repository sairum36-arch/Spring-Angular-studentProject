package com.example.demo.service;

import com.example.demo.dto.StudentTeacherViewDto;
import com.example.demo.dto.StudentUpdateByTeacherDto;
import com.example.demo.dto.TeacherProfileViewDto;
import com.example.demo.entity.Student;
import com.example.demo.entity.Teacher;
import com.example.demo.entity.User;
import com.example.demo.mapper.StudentMapper;
import com.example.demo.mapper.TeacherMapper;
import com.example.demo.repositories.StudentRepository;
import com.example.demo.repositories.TeacherRepository;
import com.example.demo.repositories.UserRepository;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
@Service

public class TeacherService {
    private final StudentRepository studentRepository;
    private final UserRepository userRepository;



    private final TeacherMapper teacherMapper;
    private final StudentMapper studentMapper;

    private final TeacherRepository teacherRepository;

    public TeacherService(StudentRepository studentRepository, UserRepository userRepository,  StudentMapper studentMapper,  TeacherRepository teacherRepository, TeacherMapper teacherMapper) {
        this.studentRepository = studentRepository;
        this.userRepository = userRepository;
        this.teacherMapper = teacherMapper;
        this.studentMapper = studentMapper;

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
//TODO: stream Map (переделано)
        return students.stream().map(studentMapper::toTeacherViewDto).toList();
    }
    public StudentTeacherViewDto updateStudentByTeacher(Long studentId, StudentUpdateByTeacherDto dto, String teacherUsername) {
        Teacher teacher = findTeacherByUsername(teacherUsername);
        Student studentToUpdate = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Студент с id: " + studentId + " не найден"));
        if (!studentToUpdate.getGroup().equals(teacher.getGroup())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Вы можете редактировать только студентов своей группы.");
        }
//TODO: реализовать частичное обновление put (сделано (см StudentMapper))
        studentMapper.updateFromDto(dto, studentToUpdate);
        Student updatedStudent = studentRepository.save(studentToUpdate);
        return studentMapper.toTeacherViewDto(updatedStudent);
    }
    //TODO: сделать в 1 запрос user/username (сделано)
    private Teacher findTeacherByUsername(String username){

        return teacherRepository.findTeacherByUsername(username).orElseThrow(()-> new ResourceNotFoundException("профиль учителя " + username + "не найден"));
    }



}
