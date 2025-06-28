
package com.example.demo.controllers;

import com.example.demo.dto.StudentCreationRequest;
import com.example.demo.entity.*;
import com.example.demo.mapper.StudentMapper;
import com.example.demo.repositories.StudentRepository;
import com.example.demo.repositories.TeacherRepository;
import com.example.demo.repositories.UserRepository;
import jakarta.persistence.GeneratedValue;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/base")





public class BaseController {

    private final StudentRepository studentRepository;
    private final UserRepository userRepository;
    private final TeacherRepository teacherRepository;
    private final PasswordEncoder passwordEncoder;
    private final StudentMapper studentMapper;


    public BaseController(StudentRepository studentRepository, UserRepository userRepository, TeacherRepository teacherRepository, PasswordEncoder passwordEncoder, StudentMapper studentMapper){
        this.studentRepository = studentRepository;
        this.userRepository = userRepository;
        this.teacherRepository = teacherRepository;
        this.passwordEncoder = passwordEncoder;
        this.studentMapper = studentMapper;
    }


    @PostMapping(value = "students", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('ADMIN')")
    public Student createStudent(@RequestBody StudentCreationRequest request) {
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Пользователь с таким username уже существует!");
        }

        Student newStudent = studentMapper.toEntity(request);
        String encodedPassword = passwordEncoder.encode(request.getPassword());
        newStudent.getUser().setPassword(new Password(encodedPassword));
        newStudent.getUser().setRole(Role.STUDENT);
        newStudent.getUser().setEnable(true);

        return studentRepository.save(newStudent);
    }


    @PutMapping(value = "/students/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Student> updateAnyStudentByAdmin(@PathVariable("id") Long id, @RequestBody Student studentDetails) {

        Student studentToUpdate = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Студент с ID " + id + " не найден"));


        studentToUpdate.setFio(studentDetails.getFio());
        studentToUpdate.setGroup(studentDetails.getGroup());
        studentToUpdate.setPhoneNumber(studentDetails.getPhoneNumber());

        if (studentDetails.getUser() != null && studentDetails.getUser().getId() != null) {
            User userToLink = userRepository.findById(studentDetails.getUser().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Пользователь с ID " + studentDetails.getUser().getId() + " не найден для привязки к студенту."));
            studentToUpdate.setUser(userToLink);
        } else {
            studentToUpdate.setUser(null);
        }

        Student updatedStudent = studentRepository.save(studentToUpdate);
        return ResponseEntity.ok(updatedStudent);
    }



    @DeleteMapping(value = "/students/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Void> deleteAnyStudentByAdmin(@PathVariable("id") Long id) {

        if (!studentRepository.existsById(id)) {
            throw new ResourceNotFoundException("Студент с ID " + id + " не найден для удаления");
        }
        studentRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/students/me")
    @PreAuthorize("hasAuthority('STUDENT')")
    public ResponseEntity<Student> getMyStudentProfile(Principal principal) {

        String username = principal.getName();

        User currentUser = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь '" + username + "' не найден"));

        Student studentProfile = studentRepository.findByUser(currentUser) // Предполагаем, что такой метод есть
                .orElseThrow(() -> new ResourceNotFoundException("Профиль студента для пользователя '" + username + "' не найден"));

        return ResponseEntity.ok(studentProfile);
    }

    @PutMapping("/students/me")
    @PreAuthorize("hasAuthority('STUDENT')")
    public ResponseEntity<Student> updateMyStudentProfile(@RequestBody Student studentUpdateRequest, Principal principal) {

        String username = principal.getName();


        User currentUser = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь '" + username + "' не найден"));

        Student existingStudent = studentRepository.findByUser(currentUser)
                .orElseThrow(() -> new ResourceNotFoundException("Профиль студента для пользователя '" + username + "' не найден"));


        existingStudent.setFio(studentUpdateRequest.getFio());
        existingStudent.setGroup(studentUpdateRequest.getGroup());
        existingStudent.setPhoneNumber(studentUpdateRequest.getPhoneNumber());


        Student updatedStudent = studentRepository.save(existingStudent);
        return ResponseEntity.ok(updatedStudent);
    }

    @GetMapping("/teachers")
    public List<Teacher>
    getAllTeachers(){
        return (List<Teacher>) teacherRepository.findAll();
    }
    @GetMapping("/teachers/{id}")
    public Teacher
    getTeacher(@PathVariable Long id){
        return teacherRepository.findById(id).orElseThrow();
    }

    @GetMapping("/students")
    public Iterable<Student> getAllStudents(){

        return studentRepository.findAll();
    }

    @GetMapping("/teachers/{id}/students")
    public List<Student> getStudentsByTeacherGroup(@PathVariable Long id) {

        Teacher teacher = teacherRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Teacher not found with id: " + id));


        if (teacher.getGroup() == null || teacher.getGroup().isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Teacher with id " + id + " has no group assigned"
            );
        }

        return studentRepository.findByGroup(teacher.getGroup());
    }
    @GetMapping("/teachers/me")
    @PreAuthorize("hasAuthority('TEACHER')")
    public ResponseEntity<Teacher> getMyTeacherProfile(Principal principal) {
        User currentUser = userRepository.findByUsername(principal.getName())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        Teacher teacher = teacherRepository.findByUser(currentUser)
                .orElseThrow(() -> new ResourceNotFoundException("Teacher not found"));

        return ResponseEntity.ok(teacher);
    }
    @GetMapping("/students/by-group/{group}")
    public List<Student> getStudentsByGroup(@PathVariable String group) {
        return studentRepository.findByGroup(group);
    }



    @PutMapping("/students/by-teacher/{studentId}")
    @PreAuthorize("hasAuthority('TEACHER')")
    public ResponseEntity<Student> updateStudentByTeacher(@PathVariable Long studentId, @RequestBody Student studentDetails, Principal principal){
        String username = principal.getName();
        Teacher teacher = teacherRepository.findByUser(userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("user not found"))).orElseThrow(() -> new ResourceNotFoundException("Teacher profile not found"));

        Student studentToUpdate = studentRepository.findById(studentId).orElseThrow(()->new ResourceNotFoundException("student  not found " + studentId));

        if(!studentToUpdate.getGroup().equals((teacher.getGroup()))){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Вы можете менять только студентов своей группы");
        }

        studentToUpdate.setFio(studentDetails.getFio());
        studentToUpdate.setPhoneNumber(studentDetails.getPhoneNumber());

        Student updatedStudent = studentRepository.save(studentToUpdate);
        return ResponseEntity.ok(updatedStudent);
    }
}
