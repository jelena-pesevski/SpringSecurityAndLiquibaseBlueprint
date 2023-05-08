package com.example.demo.security;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.demo.model.Role;
import com.example.demo.security.config.JwtConfig;
import com.example.demo.security.model.CustomUserDetails;
import com.example.demo.security.util.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Slf4j
@Component
@RequiredArgsConstructor
public class AuthorizationFilter extends OncePerRequestFilter {

  private final JwtUtil jwtUtil;
  private final JwtConfig jwtConfig;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {

    String authorizationHeader = request.getHeader(jwtConfig.getHeaderName());
    if (authorizationHeader == null || !authorizationHeader.startsWith(
        jwtConfig.getHeaderPrefix())) {
      filterChain.doFilter(request, response);
      return;
    }

    checkAuthorization(authorizationHeader, request, response, filterChain);
  }

  private void checkAuthorization(String authorizationHeader, HttpServletRequest request,
      HttpServletResponse response, FilterChain filterChain) {
    try {
      validateHeader(authorizationHeader);
      filterChain.doFilter(request, response);
    } catch (JWTVerificationException | IOException | ServletException exception) {
      log.error(
          String.format("JWT Authentication failed from: %s", request.getRemoteHost()));
      response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    }
  }

  private void validateHeader(String authorizationHeader) throws JWTVerificationException {
    String token = authorizationHeader.split(" ")[1];

    DecodedJWT decodedJWT = jwtUtil.parseJwt(token);

    CustomUserDetails customUserDetails = getCustomUserDetails(decodedJWT);

    setAuthentication(customUserDetails);
  }

  private CustomUserDetails getCustomUserDetails(DecodedJWT decodedJWT) {
    return CustomUserDetails.builder()
        .role(Role.valueOf(decodedJWT.getClaim("role").asString()))
        .id(decodedJWT.getClaim("id").asInt()).username(decodedJWT.getSubject()).build();
  }

  private void setAuthentication(CustomUserDetails customUserDetails) {
    Authentication authentication =
        new UsernamePasswordAuthenticationToken(customUserDetails, null,
            customUserDetails.getAuthorities());
    SecurityContextHolder.getContext().setAuthentication(authentication);
  }
}
