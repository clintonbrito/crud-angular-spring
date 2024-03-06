package com.clintonbrito.crudspring.dto.mapper;

import com.clintonbrito.crudspring.dto.CourseDTO;
import com.clintonbrito.crudspring.enums.Category;
import com.clintonbrito.crudspring.enums.Status;
import com.clintonbrito.crudspring.model.CourseModel;
import org.springframework.stereotype.Component;

@Component
public class CourseMapper {

    public CourseDTO toDTO(CourseModel course) {
        if (course == null) {
            return null;
        }

    return new CourseDTO(course.getId(), course.getName(), course.getCategory().getValue());
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
//        TODO: use a mapper for Category
        course.setCategory(convertCategoryValue(courseDTO.category()));
        course.setStatus(Status.ACTIVE);

        return course;

        // Builder pattern: estudar depois
    }

    public Category convertCategoryValue(String value) {
        if (value == null) {
            return null;
        }

        return switch (value) {
            case "Front-end" -> Category.FRONT_END;
            case "Back-end" -> Category.BACK_END;
            default -> throw new IllegalArgumentException("Invalid category: " + value);
        };
    }

}
