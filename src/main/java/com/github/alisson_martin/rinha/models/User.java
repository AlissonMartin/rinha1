package com.github.alisson_martin.rinha.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import lombok.Getter;
import lombok.Setter;
import java.util.UUID;
import java.util.List;

@Getter
@Setter
@Table("users")
public class User {

  @Id
  private UUID id;

  private String apelido;

  private String nome;

  private String nascimento;

  private List<String> stack;
}