package com.example.demo.repositories;

import com.example.demo.entity.Student;
import com.example.demo.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends CrudRepository<Student, Long>{
    Optional<Student> findByUser(User user);


    List<Student> findByGroup(String group);
}
