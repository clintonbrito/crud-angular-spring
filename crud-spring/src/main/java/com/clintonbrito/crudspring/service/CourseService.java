package com.clintonbrito.crudspring.service;

import com.clintonbrito.crudspring.model.CourseModel;
import com.clintonbrito.crudspring.repository.CourseRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Optional;

@Validated
@Service
public class CourseService {

    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public @ResponseBody List<CourseModel> list() {
        return courseRepository.findAll();
    }

    public Optional<CourseModel> findById(@PathVariable @NotNull @Positive Long id) {
        return courseRepository.findById(id);
    }

    public CourseModel create(@Valid CourseModel course) {
        return courseRepository.save(course);
    }

    public Optional<CourseModel> update(@NotNull @Positive Long id, @Valid CourseModel course) {
        return courseRepository.findById(id)
                .map(courseFound -> {
                    courseFound.setName(course.getName());
                    courseFound.setCategory(course.getCategory());
                    return courseRepository.save(courseFound);
                });
    }

    public boolean delete(@NotNull @Positive Long id) {
        return courseRepository.findById(id)
                .map(courseFound -> {
                    courseRepository.deleteById(id);
                    return true;
                })
                .orElse(false);
    }
}
