package com.clintonbrito.crudspring.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data // está incluso os getters e setters do Lombok
@Entity // Estou dizendo que a classe CourseModel também será uma entidade que vai fazer o mapeamento no db
@Table(name = "courses")
public class CourseModel {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  
  @Column(name = "nome", length = 200, nullable = false)
  private String name;

  @Column(name = "categoria", length = 20, nullable = false)
  private String category;

}
