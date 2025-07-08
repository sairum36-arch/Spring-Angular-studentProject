package com.example.demo.mapper;

import com.example.demo.dto.TeacherProfileViewDto;
import com.example.demo.entity.Teacher;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-07-04T13:03:56+0300",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.14.jar, environment: Java 21.0.7 (Oracle Corporation)"
)
@Component
public class TeacherMapperImpl implements TeacherMapper {

    @Override
    public TeacherProfileViewDto toProfileViewDto(Teacher teacher) {
        if ( teacher == null ) {
            return null;
        }

        TeacherProfileViewDto teacherProfileViewDto = new TeacherProfileViewDto();

        teacherProfileViewDto.setId( teacher.getId() );
        teacherProfileViewDto.setFio( teacher.getFio() );
        teacherProfileViewDto.setGroup( teacher.getGroup() );
        teacherProfileViewDto.setPhoneNumber( teacher.getPhoneNumber() );

        return teacherProfileViewDto;
    }
}
