package com.silas_mordrek.not_another_marketplace.student;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class StudentIntegrationTest {

  @Autowired
  private Environment env;

  @Autowired
  private StudentController controller;

  @Autowired
  private StudentRepository repository;

  @Test
  public void checkProperties() {
    System.out.println("=== ACTIVE PROFILES ===");
    Arrays.stream(env.getActiveProfiles()).forEach(System.out::println);

    System.out.println("=== DATASOURCE URL ===");
    System.out.println(env.getProperty("spring.datasource.url"));
  }

  @Test
  void contextLoads() {
    assertThat(controller).isNotNull();
  }

  @Test
  void shouldPersistAndRetrieveStudents () {
    // Given
    Student student = new Student(
      "Integration", "integration@test.com", LocalDate.now());

    // When
    Student addedStudent = controller.add(student);
    Student retrievedStudent = controller.getById(addedStudent.getId());

    // Then
    assertThat(retrievedStudent).isNotNull();
    assertThat(retrievedStudent.getName())
      .isEqualTo("Integration");
    assertThat(retrievedStudent.getEmail())
      .isEqualTo("integration@test.com");
  }
}
