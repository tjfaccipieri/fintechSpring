package com.tjfaccipieri.fintechspring.dto.cartao;

public record CartaoCreateDTO(
    String nome,
    String finalCartao,
    Long contaId
) {
}
