package com.example.demo.mapper;

import com.example.demo.dto.MyProfileUpdateDto;
import com.example.demo.dto.StudentUpdateByAdminDto;
import com.example.demo.dto.StudentUpdateByTeacherDto;
import com.example.demo.entity.Student;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface StudentUpdateMapper {

    void updateFromDto(StudentUpdateByAdminDto dto, @MappingTarget Student student);
    void updateFromDto(StudentUpdateByTeacherDto dto, @MappingTarget Student student);
    void updateFromDto(MyProfileUpdateDto dto, @MappingTarget Student student);
}
