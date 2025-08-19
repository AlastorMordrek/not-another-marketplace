package com.silas_mordrek.not_another_marketplace.student;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.Period;

import io.swagger.v3.oas.annotations.media.Schema;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

/**
 * Estudiantes del sistema.
 */
@Entity
@Table
public class Student {

  // Entidad ----------------------------------------------

  @Id
  @SequenceGenerator(
    name = "student_sequence",
    sequenceName = "student_sequence",
    allocationSize = 1
  )
  @GeneratedValue(
    strategy = GenerationType.SEQUENCE,
    generator = "student_sequence"
  )

  // Campos -----------------------------------------------

  private Long id;
  private String name;
  private String email;
  private LocalDate dob;
  private String rol;

  private boolean accountNonExpired;
  private boolean accountNonLocked;
  private boolean credentialsNonExpired;
  private boolean enabled;

  @Transient
  private Integer age;

  // Hides field in OpenAPI/Swagger.
  @Schema(hidden = true)
  // Allows write but blocks read.
  @JsonProperty(access = Access.WRITE_ONLY)
  private String password;


  // Constructores ----------------------------------------

  public Student () {
  }

  public Student (
    String name, String email, LocalDate dob, String password, String rol
  ) {
    this.name = name;
    this.email = email;
    this.dob = dob;
    this.rol = rol;
    this.accountNonExpired = true;
    this.accountNonLocked = true;
    this.credentialsNonExpired = true;
    this.enabled = true;
    this.password = password;
  }

  public Student (
    Long id,
    String name, String email, LocalDate dob, String password, String rol
  ) {
    this.id = id;
    this.name = name;
    this.email = email;
    this.dob = dob;
    this.rol = rol;
    this.accountNonExpired = true;
    this.accountNonLocked = true;
    this.credentialsNonExpired = true;
    this.enabled = true;
    this.password = password;
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

  public String getPassword () {
    return password;
  }

  public void setPassword (String password) {
    this.password = password;
  }

  public String getRol () {
    return rol;
  }

  public void setRol (String rol) {
    this.rol = rol;
  }

  public boolean isAccountNonExpired () {
    return accountNonExpired;
  }

  public void setAccountNonExpired (boolean accountNonExpired) {
    this.accountNonExpired = accountNonExpired;
  }

  public boolean isAccountNonLocked () {
    return accountNonLocked;
  }

  public void setAccountNonLocked (boolean accountNonLocked) {
    this.accountNonLocked = accountNonLocked;
  }

  public boolean isCredentialsNonExpired () {
    return credentialsNonExpired;
  }

  public void setCredentialsNonExpired (boolean credentialsNonExpired) {
    this.credentialsNonExpired = credentialsNonExpired;
  }

  public boolean isEnabled () {
    return enabled;
  }

  public void setEnabled (boolean enabled) {
    this.enabled = enabled;
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
