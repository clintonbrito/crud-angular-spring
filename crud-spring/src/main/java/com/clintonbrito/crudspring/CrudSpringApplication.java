package com.clintonbrito.crudspring;

import com.clintonbrito.crudspring.enums.Category;
import com.clintonbrito.crudspring.model.Course;
import com.clintonbrito.crudspring.model.Lesson;
import com.clintonbrito.crudspring.repository.CourseRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CrudSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudSpringApplication.class, args);
	}

	@Bean
	CommandLineRunner initDatabase(CourseRepository courseRepository) {
		return args -> {
			courseRepository.deleteAll();

			for (int i = 0; i < 20; i++) {
				Course c = new Course();
				c.setName("Angular com Spring " + i);
				c.setCategory(Category.FRONT_END);

				Lesson l1 = new Lesson();
				l1.setName("Angular 11");
				l1.setYoutubeUrl("3q4O5U5X3wA");
				l1.setCourse(c);
				c.getLessons().add(l1);

				Lesson l2 = new Lesson();
				l2.setName("Spring Boot 2.4.2");
				l2.setYoutubeUrl("1q4O5U5X3wZ");
				l2.setCourse(c);
				c.getLessons().add(l2);

				courseRepository.save(c);
			}
		};
		
	}

}
