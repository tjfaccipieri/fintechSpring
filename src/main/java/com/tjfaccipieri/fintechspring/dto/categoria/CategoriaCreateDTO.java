package com.tjfaccipieri.fintechspring.dto.categoria;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CategoriaCreateDTO(
  @NotNull
  @Size(min = 3, max = 20)
  String nome
) {
}
