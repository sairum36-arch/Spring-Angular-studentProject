package com.example.demo.mapper;


import com.example.demo.dto.TeacherProfileViewDto;
import com.example.demo.entity.Teacher;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TeacherMapper {
    TeacherProfileViewDto toProfileViewDto(Teacher teacher);
}
