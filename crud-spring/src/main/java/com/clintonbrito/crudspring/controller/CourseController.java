package com.clintonbrito.crudspring.controller;

import com.clintonbrito.crudspring.dto.CourseDTO;
import com.clintonbrito.crudspring.dto.CoursePageDTO;
import com.clintonbrito.crudspring.service.CourseService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated // Serve para ativar de fato as validações que estão declaradas no parâmetro dos métodos
@RestController // Já aplica automaticamente o @ResponseBody em todos os métodos
@RequestMapping("/api/courses")
public class CourseController {

    private final CourseService courseService;

  public CourseController(CourseService courseService) {
      this.courseService = courseService;
  }

  @GetMapping
//   Caso queira mudar o nome do parâmetro, é necessário informar o nome do parâmetro na anotação @RequestParam, conforme exemplo abaixo:
//  public CoursePageDTO list(@RequestParam(name = "p") int page, @RequestParam int pageSize) {
  public CoursePageDTO list(@RequestParam(defaultValue = "0") @PositiveOrZero int page,
                            @RequestParam(defaultValue = "10") @Positive @Max(100) int pageSize) {
    return courseService.list(page, pageSize);
  }

//  @GetMapping
//  public List<CourseDTO> list() {
//    return courseService.list();
//  }

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
