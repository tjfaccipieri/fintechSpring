package com.tjfaccipieri.fintechspring.dto.usuario;

import com.tjfaccipieri.fintechspring.model.UsuarioPF;

import java.util.List;

public record UsuarioPFResponseDTO(
    Long id,
    String nome,
    String email,
    String cpf,
    List<Long> contas
) {
    public UsuarioPFResponseDTO(UsuarioPF usuario, List<Long> contas) {
        this(usuario.getId(), usuario.getNome(), usuario.getEmail(), usuario.getCpf(), contas);
    }
}
