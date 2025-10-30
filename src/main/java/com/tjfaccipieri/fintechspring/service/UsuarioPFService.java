package com.tjfaccipieri.fintechspring.service;

import com.tjfaccipieri.fintechspring.dto.usuario.UsuarioPFResponseDTO;
import com.tjfaccipieri.fintechspring.model.Conta;
import com.tjfaccipieri.fintechspring.model.UsuarioPF;
import com.tjfaccipieri.fintechspring.repository.ContaRepository;
import com.tjfaccipieri.fintechspring.repository.UsuarioPFRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UsuarioPFService {
	@Autowired
	private UsuarioPFRepository repository;
	@Autowired
	private ContaRepository contaRepository;

	public Optional<UsuarioPF> findById(Long id) {
		Optional<UsuarioPF> user = repository.findById(id);
    if (user.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado.", null);
    }
    
    return user;
	}

	public UsuarioPFResponseDTO findByIdWithContas(Long id) {
		UsuarioPF user = findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado."));
		List<Long> accountIds = contaRepository.findAllByUsuario(user)
			.stream()
			.map(Conta::getId)
			.collect(Collectors.toList());

		return new UsuarioPFResponseDTO(user, accountIds);
	}

	public UsuarioPF create(UsuarioPF usuarioPF) {
		if (repository.existsByCpf(usuarioPF.getCpf())) {
			throw new DataIntegrityViolationException("CPF já cadastrado no sistema.");
		}

		return repository.save(usuarioPF);
	}

	public UsuarioPF update(UsuarioPF usuarioPF) {
		Optional<UsuarioPF> user = findById(usuarioPF.getId());
		
		if (repository.existsByCpf(usuarioPF.getCpf()) && !user.get().getId().equals(usuarioPF.getId())) {
			throw new DataIntegrityViolationException("CPF já cadastrado.");
		}

		return repository.save(usuarioPF);
	}
}
