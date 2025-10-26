package com.tjfaccipieri.fintechspring.service;

import com.tjfaccipieri.fintechspring.dto.usuario.LoginResponseDTO;
import com.tjfaccipieri.fintechspring.model.Conta;
import com.tjfaccipieri.fintechspring.model.Usuario;
import com.tjfaccipieri.fintechspring.model.UsuarioPF;
import com.tjfaccipieri.fintechspring.model.UsuarioPJ;
import com.tjfaccipieri.fintechspring.repository.ContaRepository;
import com.tjfaccipieri.fintechspring.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioService {
  @Autowired
  UsuarioRepository repository;

  @Autowired
  ContaRepository contaRepository;

  public List<Usuario> findAll() {
    return repository.findAll();
  }

  public Optional<Usuario> findById(Long id) {
    Optional<Usuario> usuario = repository.findById(id);
    if (usuario.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado.", null);
    }
    return usuario;
  }

	public void deleteById(Long id) {
		repository.deleteById(id);
	}

  public LoginResponseDTO login(String email, String senha) {
    Usuario usuario = repository.findByEmail(email)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Email ou senha inválidos."));

    if (!usuario.getSenha().equals(senha)) { // TODO: implementar uma hash de senha
      throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Email ou senha inválidos.");
    }

    String tipoUsuario;
    String documento = null;

    if (usuario instanceof UsuarioPF) {
      tipoUsuario = "PF";
      documento = ((UsuarioPF) usuario).getCpf();
    } else if (usuario instanceof UsuarioPJ) {
      tipoUsuario = "PJ";
      documento = ((UsuarioPJ) usuario).getCnpj();
    } else {
      tipoUsuario = "Desconhecido";
    }

    List<Long> contasId = contaRepository.findByUsuario(usuario).stream()
        .map(Conta::getId)
        .collect(Collectors.toList());

    return new LoginResponseDTO(
        usuario.getId(),
        usuario.getEmail(),
        usuario.getNome(),
        tipoUsuario,
        documento,
        contasId
    );
  }
}
