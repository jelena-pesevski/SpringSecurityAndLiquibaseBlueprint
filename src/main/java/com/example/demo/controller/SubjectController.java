package com.example.demo.controller;

import com.example.demo.domain.Subject;
import com.example.demo.service.SubjectService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/subjects")
@RequiredArgsConstructor
public class SubjectController {

  private final SubjectService subjectService;

  @GetMapping
  public List<Subject> getAll() {
    return subjectService.getAll();
  }

  @PostMapping
  public Subject createSubject(@RequestBody Subject subject) {
    return subjectService.createSubject(subject);
  }
}
