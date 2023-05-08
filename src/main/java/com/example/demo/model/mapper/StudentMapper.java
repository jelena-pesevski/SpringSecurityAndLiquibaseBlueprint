package com.example.demo.model.mapper;

import com.example.demo.domain.Student;
import com.example.demo.model.StudentEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StudentMapper {

  StudentEntity toEntity(Student student);

  Student toDto(StudentEntity studentEntity);

}
