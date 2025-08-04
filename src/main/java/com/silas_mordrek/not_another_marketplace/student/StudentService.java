package com.silas_mordrek.not_another_marketplace.student;

import com.silas_mordrek.not_another_marketplace.base_model.Email;
import com.silas_mordrek.not_another_marketplace.base_model.Nombre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.function.Function;

@Service
public class StudentService {

  private final StudentRepository studentRepository;


  @Autowired
  public StudentService (StudentRepository studentRepository) {
    this.studentRepository = studentRepository;
  }


  public List<Student> getAll () {
    return studentRepository.findAll();
  }


  public Student getById (Long id) {
    return studentRepository.findById(id)
      .orElseThrow(() -> new IllegalStateException("Student not found."));
  }


  public Student add (Student student) {
    if (email_taken_p(student.getEmail())) {
      throw new IllegalStateException("Email already taken.");
    }

    return studentRepository.save(student);
  }


  public Student delete (Long id) {
    return with(id, student -> {
      studentRepository.delete(student);
      return student;
    });
  }


  @Transactional
  public Student update (Long id, String name, String email) {
    return with(id, student -> {

      if (Nombre.valid_p(name) && !Objects.equals(student.getName(), name)) {
        student.setName(name);
      }
      if (Email.valid_p(email) && !Objects.equals(student.getEmail(), email)
          && !email_taken_p(email)) {
        student.setEmail(email);
      }

      return student;
    });
  }



  private Student with (Long studentId, Function<Student, Student> fun) {
    return studentRepository
        .findById(studentId)
        .map(fun)
        .orElseThrow(() -> new IllegalStateException("Student not found."));
  }

  public boolean email_taken_p (String email) {
    return studentRepository.findByEmail(email).isPresent();
  }
}
