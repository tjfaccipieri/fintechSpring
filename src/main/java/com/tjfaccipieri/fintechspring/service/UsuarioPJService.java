package com.tjfaccipieri.fintechspring.service;

import com.tjfaccipieri.fintechspring.model.UsuarioPJ;
import com.tjfaccipieri.fintechspring.repository.UsuarioPJRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class UsuarioPJService {
  @Autowired
  private UsuarioPJRepository repository;
  
  public Optional<UsuarioPJ> findById(Long id) {
    Optional<UsuarioPJ> user = repository.findById(id);
    if (user.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado.", null);
    }
    
    return user;
  }
  
  public UsuarioPJ create(UsuarioPJ usuarioPJ) {
    if (repository.existsByCnpj(usuarioPJ.getCnpj())) {
      throw new DataIntegrityViolationException("CNPJ já cadastrado no sistema.");
    }
    
    return repository.save(usuarioPJ);
  }
  
  public UsuarioPJ update(UsuarioPJ usuarioPJ) {
    Optional<UsuarioPJ> user = repository.findById(usuarioPJ.getId());
    
    if (repository.existsByCnpj(usuarioPJ.getCnpj()) && !user.get().getId().equals(usuarioPJ.getId())) {
      throw new DataIntegrityViolationException("CNPJ já cadastrado.");
    }
    
    return repository.save(usuarioPJ);
  }
}
