package com.clintonbrito.crudspring.model;

import com.clintonbrito.crudspring.enums.Category;
import com.clintonbrito.crudspring.enums.Status;
import com.clintonbrito.crudspring.enums.converters.CategoryConverter;
import com.clintonbrito.crudspring.enums.converters.StatusConverter;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import org.hibernate.validator.constraints.Length;

import java.util.ArrayList;
import java.util.List;

@Entity // Estou dizendo que a classe CourseModel também será uma entidade que vai fazer o mapeamento no db
@SQLDelete(sql = "UPDATE courses SET status = 'Inactive' WHERE id = ?")
@SQLRestriction("status <> 'Inactive'")
@Table(name = "courses")
public class Course {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @JsonProperty("_id")
  private Long id;

  @NotBlank
  @NotNull
  @Length(min = 5, max = 100)
  @Column(name = "nome", length = 100, nullable = false)
  private String name;

  @NotNull
  @Column(name = "categoria", length = 10, nullable = false)
  @Convert(converter = CategoryConverter.class)
  private Category category;

  @NotNull
  @Column(length = 10, nullable = false)
  @Convert(converter = StatusConverter.class)
  private Status status = Status.ACTIVE;

  @NotNull
  @NotEmpty
  @Valid
  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "course")
//  @JoinColumn(name = "course_id")
  private List<Lesson> lessons = new ArrayList<>();

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Category getCategory() {
    return category;
  }

  public void setCategory(Category category) {
    this.category = category;
  }

  public Status getStatus() {
    return status;
  }

  public void setStatus(Status status) {
    this.status = status;
  }

  public List<Lesson> getLessons() {
    return lessons;
  }

  public void setLessons(List<Lesson> lessons) {
    this.lessons = lessons;
  }
}
