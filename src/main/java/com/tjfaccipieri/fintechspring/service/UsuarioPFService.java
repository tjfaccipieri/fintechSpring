package com.tjfaccipieri.fintechspring.service;

import com.tjfaccipieri.fintechspring.model.UsuarioPF;
import com.tjfaccipieri.fintechspring.repository.UsuarioPFRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UsuarioPFService {
	@Autowired
	private UsuarioPFRepository repository;

	public Optional<UsuarioPF> findById(Long id) {
		return repository.findById(id);
	}

	public UsuarioPF create(UsuarioPF usuarioPF) {
		if (repository.existsByCpf(usuarioPF.getCpf())) {
			throw new DataIntegrityViolationException("CPF já cadastrado no sistema.");
		}

		return repository.save(usuarioPF);
	}

	public UsuarioPF update(UsuarioPF usuarioPF) {
		Optional<UsuarioPF> user = findById(usuarioPF.getId());
		if (user.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado.", null);
		}

		if (repository.existsByCpf(usuarioPF.getCpf()) && !user.get().getId().equals(usuarioPF.getId())) {
			throw new DataIntegrityViolationException("CPF já cadastrado com outro usuário");
		}

		return repository.save(usuarioPF);
	}
  
  public Optional<UsuarioPF> login(UsuarioPF usuario) {
    Optional<UsuarioPF> user = repository.findByEmail(usuario.getEmail());
    if (user.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado.", null);
    }
    return user;
  }
}
