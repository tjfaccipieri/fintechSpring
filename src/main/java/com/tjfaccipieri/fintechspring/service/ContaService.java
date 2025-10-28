package com.tjfaccipieri.fintechspring.service;

import com.tjfaccipieri.fintechspring.dto.conta.ContaDTO;
import com.tjfaccipieri.fintechspring.dto.conta.ContaUpdateDTO;
import com.tjfaccipieri.fintechspring.model.Conta;
import com.tjfaccipieri.fintechspring.model.Usuario;
import com.tjfaccipieri.fintechspring.repository.ContaRepository;
import com.tjfaccipieri.fintechspring.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ContaService {
  @Autowired
  private ContaRepository repository;
  
  @Autowired
  private UsuarioRepository usuarioRepository;
  
  public List<Conta> findAll() {
    return repository.findAll();
  }
  
  public List<Conta> findAllByUsuario(Usuario usuario) {
    return repository.findAllByUsuario(usuario);
  }

  public List<Conta> findAllByUsuarioId(Long usuarioId) {
    Usuario usuario = usuarioRepository.findById(usuarioId).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    return findAllByUsuario(usuario);
  }
  
  public Optional<Conta> findById(Long id) {
    Optional<Conta> conta = repository.findById(id);
    if(conta.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Conta não encontrada.", null);
    }
    return conta;
  }
  
  public Conta create(ContaDTO conta) {
    Usuario usuario = usuarioRepository.findById(conta.usuarioId()).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    Conta novaConta = new Conta();
    novaConta.setUsuario(usuario);
    novaConta.setNome(conta.nome());
    novaConta.setSaldo(conta.saldo());
    return repository.save(novaConta);
  }
  
  public Conta update(Long id, ContaUpdateDTO contaUpdateDTO) {
    Conta conta = repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Conta não encontrada."));
    conta.setNome(contaUpdateDTO.nome());
    conta.setSaldo(contaUpdateDTO.saldo());
    return repository.save(conta);
  }
  
  public void delete(Long id) {
    findById(id).ifPresent(repository::delete);
  }
}
