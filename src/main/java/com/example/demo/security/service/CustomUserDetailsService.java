package com.example.demo.security.service;

import com.example.demo.model.UserEntity;
import com.example.demo.model.mapper.UserMapper;
import com.example.demo.model.repository.UserRepository;
import com.example.demo.security.model.CustomUserDetails;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

  private final UserRepository userRepository;
  private final UserMapper userMapper;

  @Override
  public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Optional<UserEntity> userEntity=userRepository.findByUsername(username);
    return userEntity.map(userMapper::toUserDetails).orElse(null);
  }
}
