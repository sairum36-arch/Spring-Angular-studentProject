package com.example.demo.mapper;

import com.example.demo.dto.MyProfileUpdateDto;
import com.example.demo.dto.StudentUpdateByAdminDto;
import com.example.demo.dto.StudentUpdateByTeacherDto;
import com.example.demo.entity.Student;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-07-04T13:03:56+0300",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.14.jar, environment: Java 21.0.7 (Oracle Corporation)"
)
@Component
public class StudentUpdateMapperImpl implements StudentUpdateMapper {

    @Override
    public void updateFromDto(StudentUpdateByAdminDto dto, Student student) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getFio() != null ) {
            student.setFio( dto.getFio() );
        }
        if ( dto.getGroup() != null ) {
            student.setGroup( dto.getGroup() );
        }
        if ( dto.getPhoneNumber() != null ) {
            student.setPhoneNumber( dto.getPhoneNumber() );
        }
    }

    @Override
    public void updateFromDto(StudentUpdateByTeacherDto dto, Student student) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getFio() != null ) {
            student.setFio( dto.getFio() );
        }
        if ( dto.getPhoneNumber() != null ) {
            student.setPhoneNumber( dto.getPhoneNumber() );
        }
    }

    @Override
    public void updateFromDto(MyProfileUpdateDto dto, Student student) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getFio() != null ) {
            student.setFio( dto.getFio() );
        }
        if ( dto.getPhoneNumber() != null ) {
            student.setPhoneNumber( dto.getPhoneNumber() );
        }
    }
}
