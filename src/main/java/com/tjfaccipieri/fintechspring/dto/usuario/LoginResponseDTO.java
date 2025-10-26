package com.tjfaccipieri.fintechspring.dto.usuario;

import java.util.List;

public record LoginResponseDTO(
    Long id,
    String email,
    String nome,
    String tipoUsuario,
    String documento,
    List<Long> contasId
) {}