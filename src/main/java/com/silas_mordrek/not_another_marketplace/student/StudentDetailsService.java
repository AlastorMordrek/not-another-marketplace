package com.silas_mordrek.not_another_marketplace.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class StudentDetailsService implements UserDetailsService {

  private final StudentRepository studentRepository;

  @Autowired
  public StudentDetailsService (StudentRepository studentRepository) {
    this.studentRepository = studentRepository;
  }

  /**
   * @param username The username provided by the user seeking Authentication.
   * @return The UserDetails that the AuthenticationProvider will use to
   * validate the user.
   * @throws UsernameNotFoundException When the user with such username is not
   *                                   found.
   */
  @Override
  public UserDetails loadUserByUsername (String username)
    throws UsernameNotFoundException {
    return studentRepository.findByUsername(username)
      .map(student -> User.builder()
        .username(student.getEmail())
        // Password should already be encoded.
        .password(student.getPassword())
        .roles(student.getRol())
        .accountExpired(!student.isAccountNonExpired())
        .accountLocked(!student.isAccountNonLocked())
        .credentialsExpired(!student.isCredentialsNonExpired())
        .disabled(!student.isEnabled())
        .build())
      .orElseThrow(() -> new UsernameNotFoundException("Student not found"));
  }
}
