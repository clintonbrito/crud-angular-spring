package com.clintonbrito.crudspring.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

  // @RequestMapping(method = RequestMethod.GET) // mesma coisa que o @GetMapping abaixo
  @GetMapping
  public List<CourseModel> list() {
    return courseRepository.findAll();
  }

  //  @RequestMapping(method = RequestMethod.POST) // mesma coisa que o @PostMapping abaixo
  @PostMapping
  @ResponseStatus(code = HttpStatus.CREATED)
  public CourseModel create(@RequestBody CourseModel course) {
        return courseRepository.save(course);
//        return ResponseEntity.status(HttpStatus.CREATED);
//          .body(courseRepository.save(course));
  }


}
