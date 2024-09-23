package com.github.alisson_martin.rinha.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {

  @Id
  @GeneratedValue
  @UuidGenerator
  private String id;

  private String apelido;

  private String nome;

  private Date nascimento;

  private List<String> stack;

}
