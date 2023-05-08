package com.example.demo.security.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.demo.domain.JwtUser;
import com.example.demo.security.config.JwtConfig;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtUtil {

  private final JwtConfig jwtConfig;

  public String generateJwt(JwtUser user) {
    Algorithm algorithm = Algorithm.HMAC512(jwtConfig.getTokenSecret());
    return JWT.create().withSubject(user.getUsername()).withClaim("id", user.getId())
        .withClaim("role",
            user.getRole().name()).withIssuedAt(Instant.now())
        .withExpiresAt(LocalDateTime.now().plusMinutes(jwtConfig.getTokenExpirationTime()).atZone(
            ZoneId.systemDefault()).toInstant()).sign(algorithm);
  }


  public DecodedJWT parseJwt(String token) {
    Algorithm algorithm = Algorithm.HMAC512(jwtConfig.getTokenSecret());

    JWTVerifier verifier = JWT.require(algorithm).build();

    return verifier.verify(token);
  }

}
