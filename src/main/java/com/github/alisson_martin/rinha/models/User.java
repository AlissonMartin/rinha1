package com.github.alisson_martin.rinha.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "users", indexes = {
        @Index(name = "idx_apelido", columnList = "apelido"),
        @Index(name = "idx_nome", columnList = "nome"),
        @Index(name = "idx_stack", columnList = "stack")
})
@Getter
@Setter
public class User {

  @Id
  @GeneratedValue
  @UuidGenerator
  private String id;

  @Column(unique = true)
  private String apelido;

  private String nome;

  private String nascimento;

  private List<String> stack;

}
