package com.silas_mordrek.not_another_marketplace.student;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@Service
@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

  @Mock
  private StudentRepository studentRepository;

  @InjectMocks
  private StudentService studentService;


  @Test
  void add () {
    // Given
    Student student = new Student(
      "Test", "test@example.com", LocalDate.now());

    when(studentRepository.findByEmail(anyString()))
      .thenReturn(Optional.empty());

    when(studentRepository.save(any(Student.class)))
      .thenReturn(student);

    // When
    Student result = studentService.add(student);

    // Then
    assertThat(result).isNotNull();
    verify(studentRepository).save(student);
  }

  @Test
  void addShouldThrowWhenEmailExists () {
    // Given
    Student existing = new Student(
      "Existing", "exists@example.com", LocalDate.now());

    when(studentRepository.findByEmail("exists@example.com"))
      .thenReturn(Optional.of(existing));

    // When/Then
    assertThatThrownBy(() ->
      studentService.add(
        new Student("New", "exists@example.com", LocalDate.now())
      ))
      .isInstanceOf(IllegalStateException.class)
      .hasMessageContaining("Email already taken.");
  }

  @Test
  void getAllShouldReturnAllStudents () {
    // Given
    Student s1 = new Student(
      "A", "a@test.com", LocalDate.now());
    Student s2 = new Student(
      "B", "b@test.com", LocalDate.now());

    when(studentRepository.findAll()).thenReturn(List.of(s1, s2));

    // When
    List<Student> result = studentService.getAll();

    // Then
    assertThat(result).hasSize(2).containsExactly(s1, s2);
  }

  @Test
  void deleteShouldRemoveStudent () {
    // Given
    Student student = new Student(
      1L, "Test", "test@test.com", LocalDate.now());

    when(studentRepository.findById(1L))
      .thenReturn(Optional.of(student));

    // When
    Student result = studentService.delete(1L);

    // Then
    assertThat(result).isEqualTo(student);
    verify(studentRepository).delete(student);
  }
}
