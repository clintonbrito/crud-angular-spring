package com.clintonbrito.crudspring.controller;

import com.clintonbrito.crudspring.dto.CourseDTO;
import com.clintonbrito.crudspring.service.CourseService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated // Serve para ativar de fato as validações que estão declaradas no parâmetro dos métodos
@RestController
@RequestMapping("/api/courses")
public class CourseController {

    private final CourseService courseService;

  public CourseController(CourseService courseService) {
      this.courseService = courseService;
  }

  @GetMapping
  public @ResponseBody List<CourseDTO> list() {
    return courseService.list();
  }

  @GetMapping("/{id}")
  public CourseDTO findById(@PathVariable @NotNull @Positive Long id) {
    return courseService.findById(id);
  }

  @PostMapping
  @ResponseStatus(code = HttpStatus.CREATED)
  public CourseDTO create(@RequestBody @Valid @NotNull CourseDTO course) {
    return courseService.create(course);
  }

  @PutMapping("/{id}")
  public CourseDTO update(@PathVariable @NotNull @Positive Long id, @RequestBody @Valid @NotNull CourseDTO course) {
    return courseService.update(id, course);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(code = HttpStatus.NO_CONTENT)
  public void delete(@PathVariable @NotNull @Positive Long id) {
    courseService.delete(id);
  }

}
