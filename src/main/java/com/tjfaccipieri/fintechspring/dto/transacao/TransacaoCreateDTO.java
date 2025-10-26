package com.tjfaccipieri.fintechspring.dto.transacao;

import java.math.BigDecimal;

public record TransacaoCreateDTO(String descricao, BigDecimal valor, Long idConta, Long idCategoria, Long idCartao, String tipoTransacao) {
}
