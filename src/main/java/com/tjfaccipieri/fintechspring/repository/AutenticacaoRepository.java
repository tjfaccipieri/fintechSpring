package com.tjfaccipieri.fintechspring.repository;

import com.tjfaccipieri.fintechspring.model.Autenticacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AutenticacaoRepository extends JpaRepository<Autenticacao, Long> {
//  boolean existsByEmail(String email);
//  Optional<Autenticacao> findByEmailContainingIgnoreCase(String email);
}
