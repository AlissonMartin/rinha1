package com.github.alisson_martin.rinha.dtos;

import org.antlr.v4.runtime.misc.NotNull;

import java.util.Date;
import java.util.List;

public record CreateDTO(@NotNull String apelido, @NotNull String nome, @NotNull Date nascimento, List<String> stack) {
}
