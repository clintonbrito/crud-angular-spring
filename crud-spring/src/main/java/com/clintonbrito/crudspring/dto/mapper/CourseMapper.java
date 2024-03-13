package com.clintonbrito.crudspring.dto.mapper;

import com.clintonbrito.crudspring.dto.CourseDTO;
import com.clintonbrito.crudspring.dto.LessonDTO;
import com.clintonbrito.crudspring.enums.Category;
import com.clintonbrito.crudspring.enums.Status;
import com.clintonbrito.crudspring.model.Course;
import com.clintonbrito.crudspring.model.Lesson;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CourseMapper {

    public CourseDTO toDTO(Course course) {
        if (course == null) {
            return null;
        }

        List<LessonDTO> lessons = course.getLessons()
                .stream()
                .map(lesson -> new LessonDTO(lesson.getId(), lesson.getName(), lesson.getYoutubeUrl()))
//                .collect(Collectors.toList());
                .toList();

        return new CourseDTO(
                course.getId(),
                course.getName(),
                course.getCategory().getValue(),
                lessons
            );
    }

    public Course toEntity(CourseDTO courseDTO) {
        if (courseDTO == null) {
            return null;
        }

        Course course = new Course();

        if (courseDTO.id() != null) {
            course.setId(courseDTO.id());
        }

        course.setName(courseDTO.name());
        course.setCategory(convertCategoryValue(courseDTO.category()));
        course.setStatus(Status.ACTIVE);

        List<Lesson> lessons = courseDTO.lessons().stream().map(lessonDTO -> {
            var lesson = new Lesson();
            lesson.setId(lessonDTO.id());
            lesson.setName(lessonDTO.name());
            lesson.setYoutubeUrl(lessonDTO.youtubeUrl());
            lesson.setCourse(course);
            return lesson;
        }).toList();
        course.setLessons(lessons);

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
