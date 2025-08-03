package com.silas_mordrek.not_another_marketplace.student;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Service
public class StudentService {
  public List<Student> getStudents () {
    return List.of(new Student(
        1L,
        "Michael",
        "michael.jackson@example.com",
        LocalDate.of(1990, Month.JANUARY, 1),
        35
    ));
  }
}
