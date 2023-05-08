package com.example.demo.model.mapper;

import com.example.demo.domain.Subject;
import com.example.demo.model.SubjectEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SubjectMapper {

  Subject toDto(SubjectEntity subjectEntity);

  SubjectEntity toEntity(Subject subject);
}
