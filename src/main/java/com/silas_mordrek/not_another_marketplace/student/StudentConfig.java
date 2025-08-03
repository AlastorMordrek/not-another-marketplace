package com.silas_mordrek.not_another_marketplace.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

import static java.time.Month.JANUARY;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository) {
        return args -> {

            // Crear estudiantes.
            Student michael = new Student(
                    "Michael",
                    "michael.jackson@example.com",
                    LocalDate.of(1995, JANUARY, 1)
            );
            Student alex = new Student(
                    "Alexander",
                    "alex@example.com",
                    LocalDate.of(2000, JANUARY, 1)
            );

            // Guardar datos.
            repository.saveAll(List.of(michael, alex));
        };
    }
}
