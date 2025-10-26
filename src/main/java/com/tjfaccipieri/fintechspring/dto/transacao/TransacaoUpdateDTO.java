package com.tjfaccipieri.fintechspring.dto.transacao;

import java.math.BigDecimal;

public record TransacaoUpdateDTO(
    String descricao,
    BigDecimal valor,
    String tipoTransacao,
    Long idCategoria,
    Long idCartao
) {}
