package com.silas_mordrek.not_another_marketplace.rol;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class RolConfig {

  @Bean
  CommandLineRunner rolCommandLineRunner (RolRepository repository) {
    return args -> {
      // Crear estudiantes.
      Rol admin = new Rol("ADMIN");
      Rol student = new Rol("STUDENT");

      // Guardar datos.
      repository.saveAll(List.of(admin, student));
    };
  }
}
