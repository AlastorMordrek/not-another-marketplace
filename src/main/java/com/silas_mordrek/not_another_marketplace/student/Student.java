package com.silas_mordrek.not_another_marketplace.student;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.Period;

/**
 * Estudiantes del sistema.
 */
@Entity
@Table
public class Student {

  // Entidad ----------------------------------------------

  @Id
  @SequenceGenerator(
          name           = "student_sequence",
          sequenceName   = "student_sequence",
          allocationSize = 1
  )
  @GeneratedValue(
          strategy  = GenerationType.SEQUENCE,
          generator = "student_sequence"
  )

  // Campos -----------------------------------------------

  private Long      id;
  private String    name;
  private String    email;
  private LocalDate dob;

  @Transient
  private Integer   age;


  // Constructores ----------------------------------------

  public Student () {}

  public Student (
      String name, String email, LocalDate dob
  ) {
    this.name  = name;
    this.email = email;
    this.dob   = dob;
  }

  public Student (
      Long id, String name, String email, LocalDate dob
  ) {
    this.id    = id;
    this.name  = name;
    this.email = email;
    this.dob   = dob;
  }



  // Getters/Setters --------------------------------------

  public Long getId () {
    return id;
  }

  public void setId (Long id) {
    this.id = id;
  }

  public String getName () {
    return name;
  }

  public void setName (String name) {
    this.name = name;
  }

  public String getEmail () {
    return email;
  }

  public void setEmail (String email) {
    this.email = email;
  }

  public LocalDate getDob () {
    return dob;
  }

  public void setDob (LocalDate dob) {
    this.dob = dob;
  }

  public Integer getAge () {
    return Period.between(this.dob, LocalDate.now()).getYears();
  }

  public void setAge (Integer age) {
    this.age = age;
  }



  // Serializacion ----------------------------------------

  @Override
  public String toString () {
    return "Student{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", email='" + email + '\'' +
        ", dob=" + dob +
        ", age=" + age +
        '}';
  }
}
