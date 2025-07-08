package com.example.demo.mapper;

import com.example.demo.dto.StudentCreationRequest;
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
public class StudentCreationMapperImpl implements StudentCreationMapper {

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

    protected User studentCreationRequestToUser(StudentCreationRequest studentCreationRequest) {
        if ( studentCreationRequest == null ) {
            return null;
        }

        User user = new User();

        user.setUsername( studentCreationRequest.getUsername() );

        return user;
    }
}
