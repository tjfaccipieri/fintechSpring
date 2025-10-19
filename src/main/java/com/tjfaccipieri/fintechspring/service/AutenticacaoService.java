package com.tjfaccipieri.fintechspring.service;

import com.tjfaccipieri.fintechspring.dto.AutenticacaoDTO;
import com.tjfaccipieri.fintechspring.model.Autenticacao;
import com.tjfaccipieri.fintechspring.model.Usuario;
import com.tjfaccipieri.fintechspring.repository.AutenticacaoRepository;
import com.tjfaccipieri.fintechspring.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class AutenticacaoService {
  @Autowired
  private AutenticacaoRepository autenticacaoRepository;

  @Autowired
  private UsuarioRepository usuarioRepository;
  
  /*
  public Autenticacao create(AutenticacaoDTO data) {
    Usuario usuario = usuarioRepository.findById(data.usuarioId()).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    if (autenticacaoRepository.existsByEmail(data.email())) {
      throw new DataIntegrityViolationException("E-mail duplicado");
    }
    Autenticacao newAuth = new Autenticacao();
    newAuth.setEmail(data.email());
    newAuth.setSenha(data.senha());
    newAuth.setUsuario(usuario);
    return autenticacaoRepository.save(newAuth);
  }
  
  public Autenticacao login(Autenticacao autenticacao) {
    System.out.println(autenticacao);
    Optional<Autenticacao> user = autenticacaoRepository.findByEmailContainingIgnoreCase(autenticacao.getEmail());
    System.out.println(user);
    if (user.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado.", null);
    }
    
    return user.get();
  }
  
   */
}
