package com.clintonbrito.crudspring.service;

import com.clintonbrito.crudspring.dto.CourseDTO;
import com.clintonbrito.crudspring.dto.mapper.CourseMapper;
import com.clintonbrito.crudspring.exception.RecordNotFoundException;
import com.clintonbrito.crudspring.model.CourseModel;
import com.clintonbrito.crudspring.repository.CourseRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Validated
@Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;

    public CourseService(CourseRepository courseRepository, CourseMapper courseMapper) {
        this.courseRepository = courseRepository;
        this.courseMapper = courseMapper;
    }

    public List<CourseDTO> list() {
        List<CourseModel> courses = courseRepository.findAll();
        List<CourseDTO> DTOs = new ArrayList<>(courses.size());

        for (CourseModel course : courses) {
            CourseDTO DTO = new CourseDTO(course.getId(), course.getName(), course.getCategory());
            DTOs.add(DTO);
        }

        return DTOs;
    }

    public CourseModel findById(@PathVariable @NotNull @Positive Long id) {
        return courseRepository.findById(id)
            .orElseThrow(() -> new RecordNotFoundException(id));
    }

    public CourseModel create(@Valid CourseModel course) {
        return courseRepository.save(course);
    }

    public CourseModel update(@NotNull @Positive Long id, @Valid CourseModel course) {
        return courseRepository.findById(id)
            .map(courseFound -> {
                courseFound.setName(course.getName());
                courseFound.setCategory(course.getCategory());
                return courseRepository.save(courseFound);
            }).orElseThrow(() -> new RecordNotFoundException(id));
    }

    public void delete(@NotNull @Positive Long id) {

        courseRepository.delete(
            courseRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(id))
        );

    }
}
