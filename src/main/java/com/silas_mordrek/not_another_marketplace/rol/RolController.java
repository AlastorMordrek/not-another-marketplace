package com.silas_mordrek.not_another_marketplace.rol;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/rol")
public class RolController {

  private final RolService rolService;


  @Autowired
  public RolController (RolService rolService) {
    this.rolService = rolService;
  }


  @GetMapping
  public List<Rol> getAll (HttpServletRequest req) {
    return rolService.getAll();
  }

  @GetMapping(path = "{id}")
  public Rol getById (@PathVariable("id") Long id) {
    return rolService.getById(id);
  }
}
