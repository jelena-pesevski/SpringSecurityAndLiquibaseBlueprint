package com.example.demo.security.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "security.jwt")
public class JwtConfig {

  private long tokenExpirationTime;
  private String tokenSecret;
  private String headerName;
  private String headerPrefix;
}
