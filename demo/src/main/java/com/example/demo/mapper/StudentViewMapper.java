package com.example.demo.mapper;
import com.example.demo.dto.MyProfileDto;
import com.example.demo.dto.StudentAdminViewDto;
import com.example.demo.dto.StudentCreationRequest;
import com.example.demo.dto.StudentTeacherViewDto;
import com.example.demo.entity.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;


@Mapper(componentModel = "spring")
public interface StudentViewMapper {

    @Mappings({
            @Mapping(target = "studentId", source = "id"),
            @Mapping(target = "username", source = "user.username")
    })
    StudentAdminViewDto toAdminViewDto(Student student);


    @Mapping(target = "studentId", source = "id")
    StudentTeacherViewDto toTeacherViewDto(Student student);


    MyProfileDto toMyProfileDto(Student student);

}