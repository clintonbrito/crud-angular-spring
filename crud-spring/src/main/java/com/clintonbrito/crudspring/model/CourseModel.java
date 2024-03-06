package com.clintonbrito.crudspring.model;

import com.clintonbrito.crudspring.enums.Category;
import com.clintonbrito.crudspring.enums.Status;
import com.clintonbrito.crudspring.enums.converters.CategoryConverter;
import com.clintonbrito.crudspring.enums.converters.StatusConverter;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import org.hibernate.validator.constraints.Length;

@Data // está incluso os getters e setters do Lombok
@Entity // Estou dizendo que a classe CourseModel também será uma entidade que vai fazer o mapeamento no db
@SQLDelete(sql = "UPDATE courses SET status = 'Inactive' WHERE id = ?")
@SQLRestriction("status <> 'Inactive'")
@Table(name = "courses")
public class CourseModel {

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

}
