package com.silas_mordrek.not_another_marketplace.login;

import com.silas_mordrek.not_another_marketplace.student.Student;
import com.silas_mordrek.not_another_marketplace.student.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "login")
public class LoginController {

  private final StudentService studentService;


  @Autowired
  public LoginController (StudentService studentService) {
    this.studentService = studentService;
  }


  @PostMapping
  public String login (@RequestBody Student student) {
    return studentService.verify(student);
  }
}
