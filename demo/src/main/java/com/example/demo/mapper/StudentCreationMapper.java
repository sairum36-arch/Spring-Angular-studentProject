package com.example.demo.mapper;
import com.example.demo.dto.StudentCreationRequest;
import com.example.demo.entity.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface StudentCreationMapper {

    @Mapping(target = "user", source = "request")
    @Mapping(target = "user.username", source = "username")
    Student toEntity(StudentCreationRequest request);
}
