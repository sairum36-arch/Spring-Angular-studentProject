package com.example.demo.mapper;

import com.example.demo.dto.MyProfileDto;
import com.example.demo.dto.StudentAdminViewDto;
import com.example.demo.dto.StudentTeacherViewDto;
import com.example.demo.entity.Student;
import com.example.demo.entity.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-07-04T13:03:56+0300",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.14.jar, environment: Java 21.0.7 (Oracle Corporation)"
)
@Component
public class StudentViewMapperImpl implements StudentViewMapper {

    @Override
    public StudentAdminViewDto toAdminViewDto(Student student) {
        if ( student == null ) {
            return null;
        }

        StudentAdminViewDto studentAdminViewDto = new StudentAdminViewDto();

        studentAdminViewDto.setStudentId( student.getId() );
        studentAdminViewDto.setUsername( studentUserUsername( student ) );
        studentAdminViewDto.setFio( student.getFio() );
        studentAdminViewDto.setGroup( student.getGroup() );
        studentAdminViewDto.setPhoneNumber( student.getPhoneNumber() );

        return studentAdminViewDto;
    }

    @Override
    public StudentTeacherViewDto toTeacherViewDto(Student student) {
        if ( student == null ) {
            return null;
        }

        StudentTeacherViewDto studentTeacherViewDto = new StudentTeacherViewDto();

        studentTeacherViewDto.setStudentId( student.getId() );
        studentTeacherViewDto.setFio( student.getFio() );
        studentTeacherViewDto.setGroup( student.getGroup() );
        studentTeacherViewDto.setPhoneNumber( student.getPhoneNumber() );

        return studentTeacherViewDto;
    }

    @Override
    public MyProfileDto toMyProfileDto(Student student) {
        if ( student == null ) {
            return null;
        }

        MyProfileDto myProfileDto = new MyProfileDto();

        myProfileDto.setFio( student.getFio() );
        myProfileDto.setGroup( student.getGroup() );
        myProfileDto.setPhoneNumber( student.getPhoneNumber() );

        return myProfileDto;
    }

    private String studentUserUsername(Student student) {
        if ( student == null ) {
            return null;
        }
        User user = student.getUser();
        if ( user == null ) {
            return null;
        }
        String username = user.getUsername();
        if ( username == null ) {
            return null;
        }
        return username;
    }
}
