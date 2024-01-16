package com.clintonbrito.crudspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.clintonbrito.crudspring.model.CourseModel;

@Repository
public interface CourseRepository extends JpaRepository<CourseModel, Long> {
  
}
