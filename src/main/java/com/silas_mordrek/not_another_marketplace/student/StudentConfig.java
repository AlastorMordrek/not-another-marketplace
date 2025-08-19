package com.silas_mordrek.not_another_marketplace.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

import static java.time.Month.JANUARY;

@Configuration
public class StudentConfig {

  @Bean
  CommandLineRunner studentCommandLineRunner (
    StudentRepository repository,
    StudentService studentService
  ) {
    return args -> {

      Student michael = new Student(
        "Michael",
        "michael.jackson@example.com",
        LocalDate.of(1995, JANUARY, 1),
        "pwd",
        "ADMIN"
      );
      Student alex = new Student(
        "Alexander",
        "alex@example.com",
        LocalDate.of(2000, JANUARY, 1),
        "pwd",
        "STUDENT"
      );

      studentService.add(michael);
      studentService.add(alex);
    };
  }
}
