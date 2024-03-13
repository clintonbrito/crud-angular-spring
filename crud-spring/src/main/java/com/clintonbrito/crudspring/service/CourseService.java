package com.clintonbrito.crudspring.service;

import com.clintonbrito.crudspring.dto.CourseDTO;
import com.clintonbrito.crudspring.dto.mapper.CourseMapper;
import com.clintonbrito.crudspring.exception.RecordNotFoundException;
import com.clintonbrito.crudspring.model.Course;
import com.clintonbrito.crudspring.repository.CourseRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

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
        return courseRepository.findAll().stream()
            .map(courseMapper::toDTO)
//            .collect(Collectors.toList());
            .toList();
    }

    public CourseDTO findById(@NotNull @Positive Long id) {
        return courseRepository.findById(id)
            .map(courseMapper::toDTO)
            .orElseThrow(() -> new RecordNotFoundException(id));
    }

    public CourseDTO create(@Valid @NotNull CourseDTO course) {
        return courseMapper.toDTO(courseRepository.save(courseMapper.toEntity(course)));
    }

    public CourseDTO update(@NotNull @Positive Long id, @Valid @NotNull CourseDTO courseDTO) {
        return courseRepository.findById(id)
            .map(courseFound -> {
                Course course = courseMapper.toEntity(courseDTO);
                courseFound.setName(courseDTO.name());
                courseFound.setCategory(courseMapper.convertCategoryValue(courseDTO.category()));

                courseFound.getLessons().clear();
                course.getLessons().forEach(lessonFound -> courseFound.getLessons().add(lessonFound));
//                poderia ser feito desse jeito também:
//                course.getLessons().forEach(courseFound.getLessons()::add);

                return courseMapper.toDTO(courseRepository.save(courseFound));
            }).orElseThrow(() -> new RecordNotFoundException(id));
    }

    public void delete(@NotNull @Positive Long id) {

        courseRepository.delete(
            courseRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(id))
        );

    }
}
