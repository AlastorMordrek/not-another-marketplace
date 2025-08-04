package com.silas_mordrek.not_another_marketplace.student;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class StudentRepositoryTest {

  @Autowired
  private StudentRepository studentRepository;

  @Test
  void findByEmailShouldReturnStudent () {
    // Given
    var email = "test@example.com";
    Student student = new Student("Test", email, LocalDate.now());
    studentRepository.save(student);

    // When
    Optional<Student> found = studentRepository.findByEmail(email);

    // Then
    assertThat(found).isPresent();
    assertThat(found.get().getEmail()).isEqualTo(student.getEmail());
  }
}
