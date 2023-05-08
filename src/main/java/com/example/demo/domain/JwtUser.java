package com.example.demo.domain;

import lombok.Data;

@Data
public class JwtUser extends User{

  private String jwt;
}
