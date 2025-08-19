package com.silas_mordrek.not_another_marketplace.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

  private final UserDetailsService userDetailsService;
//  private final JwtFilter jwtFilter;


  @Autowired
  public WebSecurityConfig (
    UserDetailsService userDetailsService
//    , JwtFilter jwtFilter
  ) {
    this.userDetailsService = userDetailsService;
//    this.jwtFilter = jwtFilter;
  }


  @Bean
  public SecurityFilterChain securityFilterChain (HttpSecurity http)
    throws Exception {
    return http
      .csrf(AbstractHttpConfigurer::disable)

      .authorizeHttpRequests(req ->
        req
          .requestMatchers(
            // Authentication endpoints.
            "/login",
            "/api/v1/student/login",
            "/api/v1/student/logout",
            "/api/v1/student/register",

            // Public Swagger and OpenAPI endpoints.
            "/swagger-ui.html",
            "/swagger-ui/**",
            "/v3/api-docs/**")
          .permitAll()

          // All other endpoints.
          .anyRequest().authenticated())

//      .formLogin(withDefaults())

      .httpBasic(withDefaults())

      .sessionManagement(sesion ->
        sesion.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

//      .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)

      .userDetailsService(userDetailsService)

      .build();
  }


  @Bean
  public PasswordEncoder passwordEncoder () {
    return new BCryptPasswordEncoder(10);
  }

  @Bean
  public AuthenticationManager authenticationManager (
    AuthenticationConfiguration config
  ) throws Exception {
    return config.getAuthenticationManager();
  }

//  @Bean
//  public UserDetailsPasswordService userDetailsPasswordService () {
//    // This only comes into play when you need to upgrade the encoder for your
//    // passwords.
//    return new StudentDetailsPasswordService(
//      userRepository(), passwordEncoder());
//  }

//  @Bean
//  public AuthenticationProvider authenticationProvider () {
//    // This is the old way of authentication through a provider.
//    // Direct handling through a UserDetailsProvider is the new way.
//
//    DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
//
//    provider.setPasswordEncoder(passwordEncoder());
//
//    provider.setUserDetailsService(userDetailsService());
//
//    return provider;
//  }
}
