package com.example.demo.controller;

import com.example.demo.domain.JwtUser;
import com.example.demo.domain.requests.LoginRequest;
import com.example.demo.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

  private final AuthService authService;

  @PostMapping("/login")
  public JwtUser login(@RequestBody LoginRequest loginRequest){
    return authService.login(loginRequest);
  }

}
