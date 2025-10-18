package com.tjfaccipieri.fintechspring.service;

import com.tjfaccipieri.fintechspring.model.Usuario;
import com.tjfaccipieri.fintechspring.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
  @Autowired
  UsuarioRepository repository;
  
  public List<Usuario> findAll() {
    return repository.findAll();
  }
  
  public Optional<Usuario> findById(Long id) {
    return repository.findById(id);
  }

	public void deleteById(Long id) {
		repository.deleteById(id);
	}
}
