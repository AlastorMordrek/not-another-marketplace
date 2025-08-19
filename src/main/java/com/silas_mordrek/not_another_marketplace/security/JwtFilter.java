package com.silas_mordrek.not_another_marketplace.security;

import com.silas_mordrek.not_another_marketplace.student.StudentDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.context.ApplicationContext;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

  private final JWTService jwtService;
  private final ApplicationContext context;


  public JwtFilter (
    JWTService jwtService,
    ApplicationContext context
  ) {
    this.jwtService = jwtService;
    this.context = context;
  }


  @Override
  protected void doFilterInternal (
    @NonNull HttpServletRequest request,
    @NonNull HttpServletResponse response,
    @NonNull FilterChain filterChain
  )
    throws ServletException, IOException {
    String authorizationHeader = request.getHeader("Authorization");
    String token_str = (
      authorizationHeader != null && authorizationHeader.startsWith("Bearer ")
        ? authorizationHeader.substring(7)
        : null);
    String username =
      token_str == null ? null : jwtService.extractUsername(token_str);

    if (username != null
      && SecurityContextHolder.getContext().getAuthentication() == null) {

      UserDetails userDetails = context
        .getBean(StudentDetailsService.class).loadUserByUsername(username);

      if(jwtService.validateToken(token_str, userDetails)) {
        UsernamePasswordAuthenticationToken token =
          new UsernamePasswordAuthenticationToken(
            userDetails, null, userDetails.getAuthorities());
        token.setDetails(
          new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(token);
      }
    }
    filterChain.doFilter(request, response);
  }
}
