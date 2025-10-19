package com.tjfaccipieri.fintechspring.dto.cartao;

import com.tjfaccipieri.fintechspring.dto.conta.ContaResponseDTO;

public record CartaoResponseDTO(
    Long id,
    String nome,
    String finalCartao,
    ContaResponseDTO conta
) {
}
