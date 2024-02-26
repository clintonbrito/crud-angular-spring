package com.clintonbrito.crudspring.controller;

import java.util.List;

import com.clintonbrito.crudspring.service.CourseService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.clintonbrito.crudspring.model.CourseModel;
import com.clintonbrito.crudspring.repository.CourseRepository;

@Validated // Serve para ativar de fato as validações que estão declaradas no parâmetro dos métodos
@RestController
@RequestMapping("/api/courses")
public class CourseController {

    private final CourseService courseService;

  public CourseController(CourseService courseService) {
      this.courseService = courseService;
  }

  @GetMapping
  public List<CourseModel> list() {
    return courseService.list();
  }

  @GetMapping("/{id}")
  public ResponseEntity<CourseModel> findById(@PathVariable @NotNull @Positive Long id) {
    return courseService.findById(id)
            .map(courseFound -> ResponseEntity.ok().body(courseFound))
            .orElse(ResponseEntity.notFound().build());
  }

  @PostMapping
  @ResponseStatus(code = HttpStatus.CREATED)
  public CourseModel create(@RequestBody @Valid CourseModel course) {
    return courseService.create(course);
  }

  @PutMapping("/{id}")
  public ResponseEntity<CourseModel> update(@PathVariable @NotNull @Positive Long id, @RequestBody @Valid CourseModel course) {
    return courseService.update(id, course)
            .map(courseFound -> ResponseEntity.ok().body(courseFound))
            .orElse(ResponseEntity.notFound().build());
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable @NotNull @Positive Long id) {
    if (courseService.delete(id)) {
        return ResponseEntity.noContent().<Void>build();
    }
    return ResponseEntity.notFound().build();
  }

}
