package com.silas_mordrek.not_another_marketplace.rol;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RolRepository extends JpaRepository<Rol, Long> {

  @Query("SELECT s FROM Rol s WHERE s.name = ?1")
  Optional<Rol> findByName (String name);
}
