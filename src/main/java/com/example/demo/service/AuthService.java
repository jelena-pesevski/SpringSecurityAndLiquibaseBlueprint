package com.example.demo.service;

import com.example.demo.domain.JwtUser;
import com.example.demo.domain.requests.LoginRequest;
import com.example.demo.model.mapper.UserMapper;
import com.example.demo.security.model.CustomUserDetails;
import com.example.demo.security.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

  private final AuthenticationManager authenticationManager;

  private final UserMapper userMapper;

  private final JwtUtil jwtUtil;

  public JwtUser login(LoginRequest loginRequest) {
    CustomUserDetails principal = getCustomUserDetailsObject(loginRequest);

    JwtUser jwtUser = userMapper.toDto(principal);

    jwtUser.setJwt(jwtUtil.generateJwt(jwtUser));

    return jwtUser;
  }

  private CustomUserDetails getCustomUserDetailsObject(LoginRequest loginRequest) {
    final var authentication =
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                loginRequest.getUsername(), loginRequest.getPassword()));
    return (CustomUserDetails) authentication.getPrincipal();
  }
}
