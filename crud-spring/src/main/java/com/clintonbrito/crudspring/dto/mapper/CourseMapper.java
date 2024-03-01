package com.clintonbrito.crudspring.dto.mapper;

import com.clintonbrito.crudspring.dto.CourseDTO;
import com.clintonbrito.crudspring.model.CourseModel;
import org.springframework.stereotype.Component;

@Component
public class CourseMapper {

    public CourseDTO toDTO(CourseModel course) {
        if (course == null) {
            return null;
        }

        return new CourseDTO(course.getId(), course.getName(), course.getCategory());
    }

    public CourseModel toEntity(CourseDTO courseDTO) {
        if (courseDTO == null) {
            return null;
        }

        CourseModel course = new CourseModel();

        if (courseDTO.id() != null) {
            course.setId(courseDTO.id());
        }

        course.setName(courseDTO.name());
        course.setCategory(courseDTO.category());
        course.setStatus("Active");

        return course;

        // Builder pattern: estudar depois
    }

}
