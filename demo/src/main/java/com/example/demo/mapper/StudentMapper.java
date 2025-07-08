package com.example.demo.mapper;
import com.example.demo.dto.*;
import com.example.demo.entity.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;


@Mapper(
        componentModel = "spring",
nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface StudentMapper {
    
    @Mapping(target = "studentId", source = "id")
    @Mapping(target = "username", source = "user.username")
    StudentAdminViewDto toAdminViewDto(Student student);

    @Mapping(target = "studentId", source = "id")
    StudentTeacherViewDto toTeacherViewDto(Student student);

    @Mapping(target = "user", source = "request")
    @Mapping(target = "user.username", source = "username")
    Student toEntity(StudentCreationRequest request);

    void updateFromDto(StudentUpdateByAdminDto dto, @MappingTarget Student student);
    void updateFromDto(StudentUpdateByTeacherDto dto, @MappingTarget Student student);
    void updateFromDto(MyProfileUpdateDto dto, @MappingTarget Student student);

    MyProfileDto toMyProfileDto(Student updatedStudent);
}
