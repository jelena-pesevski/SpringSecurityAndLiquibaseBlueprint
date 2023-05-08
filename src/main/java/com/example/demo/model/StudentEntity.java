package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "student")
public class StudentEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "stud_gen")
  @SequenceGenerator(name = "stud_gen", sequenceName = "stud_seq", allocationSize = 1)
  private Integer id;
  private String firstName;
  private String lastName;
}
