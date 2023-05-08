package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "subject")
public class SubjectEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "subj_gen")
  @SequenceGenerator(name = "subj_gen", sequenceName = "subj_seq", allocationSize = 1)
  private Integer id;

  private String name;
  private Integer ects;
}
