package com.clintonbrito.crudspring.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clintonbrito.crudspring.model.CourseModel;
import com.clintonbrito.crudspring.repository.CourseRepository;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

  private final CourseRepository courseRepository;

  // Construtor da classe CourseController que recebe um objeto do tipo CourseRepository e atribui a variável courseRepository
  public CourseController(CourseRepository courseRepository) {
    this.courseRepository = courseRepository;
  }

  // @RequestMapping(method = RequestMethod.GET) // mesma coisa que @GetMapping
  @GetMapping
  public List<CourseModel> list() {
    return courseRepository.findAll();
  }
  
}
