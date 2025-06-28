package com.example.demo.repositories;

import com.example.demo.entity.Teacher;
import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface TeacherRepository extends CrudRepository<Teacher, Long> {
    Optional<Teacher> findByUser(User user);


    @Query("SELECT t FROM Teacher t WHERE t.user.id = :userId")
    Optional<Teacher>
    findByUserId(Long userId);

    List<Teacher>
    findByGroup(String group);


}
