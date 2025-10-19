package com.tjfaccipieri.fintechspring.repository;

import com.tjfaccipieri.fintechspring.model.Conta;
import com.tjfaccipieri.fintechspring.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContaRepository extends JpaRepository<Conta, Long> {
  List<Conta> findByUsuario(Usuario usuario);
}
