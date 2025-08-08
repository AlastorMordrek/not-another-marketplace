package com.silas_mordrek.not_another_marketplace.rol;

import jakarta.persistence.*;

/**
 * Roles del sistema.
 */
@Entity
@Table
public class Rol {

  // Entidad ----------------------------------------------

  @Id
  @SequenceGenerator(
    name = "rol_sequence",
    sequenceName = "rol_sequence",
    allocationSize = 1
  )
  @GeneratedValue(
    strategy = GenerationType.SEQUENCE,
    generator = "rol_sequence"
  )

  // Campos -----------------------------------------------

  private Long         id;
  private String       name;


  // Constructores ----------------------------------------

  public Rol () {}

  public Rol (String name) {
    this.name  = name;
  }

  public Rol (
    Long id, String name
  ) {
    this.id    = id;
    this.name  = name;
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



  // Serializacion ----------------------------------------

  @Override
  public String toString () {
    return "Rol{" +
      "id=" + id +
      ", name='" + name + '\'' +
      '}';
  }
}
