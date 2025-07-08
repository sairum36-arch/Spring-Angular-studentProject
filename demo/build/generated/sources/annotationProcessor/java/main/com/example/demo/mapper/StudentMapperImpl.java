package com.example.demo.mapper;

import com.example.demo.dto.MyProfileDto;
import com.example.demo.dto.MyProfileUpdateDto;
import com.example.demo.dto.StudentAdminViewDto;
import com.example.demo.dto.StudentCreationRequest;
import com.example.demo.dto.StudentTeacherViewDto;
import com.example.demo.dto.StudentUpdateByAdminDto;
import com.example.demo.dto.StudentUpdateByTeacherDto;
import com.example.demo.entity.Student;
import com.example.demo.entity.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-07-08T13:19:56+0300",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.14.jar, environment: Java 21.0.7 (Oracle Corporation)"
)
@Component
public class StudentMapperImpl implements StudentMapper {

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
    public Student toEntity(StudentCreationRequest request) {
        if ( request == null ) {
            return null;
        }

        Student student = new Student();

        student.setUser( studentCreationRequestToUser( request ) );
        student.setFio( request.getFio() );
        student.setGroup( request.getGroup() );
        student.setPhoneNumber( request.getPhoneNumber() );

        return student;
    }

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

    @Override
    public MyProfileDto toMyProfileDto(Student updatedStudent) {
        if ( updatedStudent == null ) {
            return null;
        }

        MyProfileDto myProfileDto = new MyProfileDto();

        myProfileDto.setFio( updatedStudent.getFio() );
        myProfileDto.setGroup( updatedStudent.getGroup() );
        myProfileDto.setPhoneNumber( updatedStudent.getPhoneNumber() );

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

    protected User studentCreationRequestToUser(StudentCreationRequest studentCreationRequest) {
        if ( studentCreationRequest == null ) {
            return null;
        }

        User user = new User();

        user.setUsername( studentCreationRequest.getUsername() );

        return user;
    }
}
