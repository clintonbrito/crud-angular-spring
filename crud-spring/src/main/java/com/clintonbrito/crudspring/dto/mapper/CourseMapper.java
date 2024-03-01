package com.clintonbrito.crudspring.dto.mapper;

import com.clintonbrito.crudspring.dto.CourseDTO;
import com.clintonbrito.crudspring.model.CourseModel;
import org.springframework.stereotype.Component;

@Component
public class CourseMapper {

    public CourseDTO toDTO(CourseModel course) {
        return new CourseDTO(course.getId(), course.getName(), course.getCategory());
    }

}
