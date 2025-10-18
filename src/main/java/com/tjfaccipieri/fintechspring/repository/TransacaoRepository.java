package com.tjfaccipieri.fintechspring.repository;

import com.tjfaccipieri.fintechspring.model.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransacaoRepository extends JpaRepository<Transacao, Long> {

}
