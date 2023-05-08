package com.example.demo.service;

import com.example.demo.domain.Subject;
import com.example.demo.model.SubjectEntity;
import com.example.demo.model.mapper.SubjectMapper;
import com.example.demo.model.repository.SubjectRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class SubjectService {

  private final SubjectRepository subjectRepository;
  private final SubjectMapper subjectMapper;

  public List<Subject> getAll(){
    return subjectRepository.findAll().stream().map(subjectMapper::toDto).toList();
  }

  public Subject createSubject(Subject subject) {
    log.info("Adding new student... ");
    SubjectEntity subjectEntity = subjectRepository.save(subjectMapper.toEntity(subject));
    return subjectMapper.toDto(subjectEntity);
  }

}
