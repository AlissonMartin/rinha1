package com.github.alisson_martin.rinha.dtos;


import java.util.Date;
import java.util.List;

public record CreateDTO(String apelido, String nome, Date nascimento, List<String> stack) {
}
