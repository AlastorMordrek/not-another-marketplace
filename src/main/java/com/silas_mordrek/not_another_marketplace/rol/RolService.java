package com.silas_mordrek.not_another_marketplace.rol;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolService {

  private final RolRepository rolRepository;


  @Autowired
  public RolService (RolRepository rolRepository) {
    this.rolRepository = rolRepository;
  }


  public List<Rol> getAll () {
    return rolRepository.findAll();
  }


  public Rol getById (Long id) {
    return rolRepository.findById(id)
      .orElseThrow(() -> new IllegalStateException("Rol not found."));
  }


  public Rol add (Rol rol) {
    if (name_taken_p(rol.getName())) {
      throw new IllegalStateException("Rol name already taken.");
    }

    return rolRepository.save(rol);
  }


  public boolean name_taken_p (String email) {
    return rolRepository.findByName(email).isPresent();
  }
}
