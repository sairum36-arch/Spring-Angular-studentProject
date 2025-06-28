package com.example.demo.mapper;


import com.example.demo.dto.StudentCreationRequest;
import com.example.demo.entity.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import javax.swing.*;

@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface StudentMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user.username", source = "username")
    @Mapping(target = "user.id", ignore = true)
    @Mapping(target = "user.password", ignore = true)
    @Mapping(target="user.role", ignore = true)
    @Mapping(target = "user.enable", ignore = true)
    Student toEntity(StudentCreationRequest request);
}
