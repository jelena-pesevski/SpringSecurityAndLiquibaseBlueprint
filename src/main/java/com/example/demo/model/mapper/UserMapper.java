package com.example.demo.model.mapper;

import com.example.demo.domain.JwtUser;
import com.example.demo.domain.User;
import com.example.demo.model.UserEntity;
import com.example.demo.security.model.CustomUserDetails;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

  CustomUserDetails toUserDetails(UserEntity userEntity);

  User toDto(UserEntity userEntity);

  UserEntity toEntity(User user);

  JwtUser toDto(CustomUserDetails customUserDetails);
}
