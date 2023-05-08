package com.example.demo.domain;

import com.example.demo.model.Role;
import lombok.Data;

@Data
public class User {

  private Integer id;
  private String username;
  private Role role;
}
