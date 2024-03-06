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

			Course c = new Course();
			c.setName("Angular com Spring");
			c.setCategory(Category.FRONT_END);

			Lesson l = new Lesson();
			l.setName("Angular 11");
			l.setYoutubeUrl("3q4O5U5X3wA");
			l.setCourse(c);
			c.getLessons().add(l);

			courseRepository.save(c);
		};
		
	}

}
