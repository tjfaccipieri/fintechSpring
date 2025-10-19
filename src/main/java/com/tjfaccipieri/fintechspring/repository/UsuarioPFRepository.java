package com.tjfaccipieri.fintechspring.repository;


import com.tjfaccipieri.fintechspring.model.UsuarioPF;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioPFRepository extends JpaRepository<UsuarioPF, Long> {
  boolean existsByCpf(String cpf);
  Optional<UsuarioPF> findByEmail(String email);
}
