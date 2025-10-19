package com.tjfaccipieri.fintechspring.dto.conta;

import java.math.BigDecimal;

public record ContaUpdateDTO(String nome, BigDecimal saldo) {
}
