package com.tjfaccipieri.fintechspring.dto.conta;

import java.math.BigDecimal;

public record ContaDTO(String nome, BigDecimal saldo, Long usuarioId) {
}
