package com.tjfaccipieri.fintechspring.repository;

import com.tjfaccipieri.fintechspring.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}
