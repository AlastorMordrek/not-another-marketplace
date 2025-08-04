package com.silas_mordrek.not_another_marketplace.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/student")

public class StudentController {

  private final StudentService studentService;


  @Autowired
  public StudentController (StudentService studentService) {
    this.studentService = studentService;
  }


  @GetMapping
  public List<Student> getAll () {
    return studentService.getAll();
  }

  @GetMapping(path = "{id}")
  public Student getById (@PathVariable("id") Long id) {
    return studentService.getById(id);
  }

  @PostMapping
  public Student add (@RequestBody Student _student) {
    return studentService.add(_student);
  }

  @DeleteMapping(path = {"{id}"})
  public Student delete (@PathVariable("id") Long id) {
    return studentService.delete(id);
  }

  @PutMapping(path = "{id}")
  public Student update (
    @PathVariable("id") Long id,
    @RequestParam(required = false) String name,
    @RequestParam(required = false) String email
  ) {
    return studentService.update(id, name, email);
  }
}
