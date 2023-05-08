package com.example.demo.model.repository;

import com.example.demo.model.SubjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<SubjectEntity, Integer> {

}
