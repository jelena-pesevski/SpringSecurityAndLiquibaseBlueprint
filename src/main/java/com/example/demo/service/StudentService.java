package com.example.demo.service;

import com.example.demo.domain.JwtUser;
import com.example.demo.domain.Student;
import com.example.demo.domain.requests.LoginRequest;
import com.example.demo.model.StudentEntity;
import com.example.demo.model.mapper.StudentMapper;
import com.example.demo.model.repository.StudentRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Slf4j
@Service
@RequiredArgsConstructor
public class StudentService {

  private final StudentRepository studentRepository;

  private final StudentMapper studentMapper;

  private final AuthService authService;

  @PostMapping("/login")
  public JwtUser login(@RequestBody LoginRequest loginRequest){
    log.info("Into the controller");
    return authService.login(loginRequest);
  }


  public List<Student> getAll() {
    return studentRepository.findAll().stream().map(studentMapper::toDto).toList();
  }

  public Student createStudent(Student student) {
    log.info("Adding new student... ");
    StudentEntity studentEntity = studentRepository.save(studentMapper.toEntity(student));
    return studentMapper.toDto(studentEntity);
  }

}
