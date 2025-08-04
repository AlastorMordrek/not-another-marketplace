package com.silas_mordrek.not_another_marketplace.student;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(StudentController.class)
class StudentControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockitoBean
  private StudentService studentService;

  @Test
  void getAllSholdReturnStudents () throws Exception {
    // Given
    Student student = new Student(
      1L, "Test", "test@example.com", LocalDate.now());

    when(studentService.getAll()).thenReturn(List.of(student));

    // When/Then
    mockMvc.perform(get("/api/v1/student"))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$[0].name").value(student.getName()))
      .andExpect(jsonPath("$[0].email").value(student.getEmail()));
  }
}
