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

  public CourseController(CourseRepository courseRepository) {
    this.courseRepository = courseRepository;
  }

  @GetMapping
  public List<CourseModel> list() {
    return courseRepository.findAll();
  }

  @GetMapping("/{id}")
  public ResponseEntity<CourseModel> findById(@PathVariable Long id) {
    return courseRepository.findById(id)
            .map(courseFound -> ResponseEntity.ok().body(courseFound))
            .orElse(ResponseEntity.notFound().build());
  }

  @PostMapping
  @ResponseStatus(code = HttpStatus.CREATED)
  public CourseModel create(@RequestBody CourseModel course) {
    return courseRepository.save(course);
  }

  @PutMapping("/{id}")
  public ResponseEntity<CourseModel> update(@PathVariable Long id, @RequestBody CourseModel course) {
    return courseRepository.findById(id)
            .map(courseFound -> {
              courseFound.setName(course.getName());
              courseFound.setCategory(course.getCategory());
              CourseModel courseUpdated = courseRepository.save(courseFound);
              return ResponseEntity.ok().body(courseUpdated);
            })
            .orElse(ResponseEntity.notFound().build());
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    return courseRepository.findById(id)
            .map(courseFound -> {
              courseRepository.deleteById(id);
              return ResponseEntity.noContent().<Void>build();
            })
            .orElse(ResponseEntity.notFound().build());
  }

}
