package com.tjfaccipieri.fintechspring.controller;

import com.tjfaccipieri.fintechspring.model.Usuario;
import com.tjfaccipieri.fintechspring.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
  @Autowired
  private UsuarioService service;
  
  @GetMapping()
  public ResponseEntity<List<Usuario>> findAll() {
    return ResponseEntity.ok(service.findAll());
  }
  
  @GetMapping("/{id}")
  public ResponseEntity<Usuario> findById(@PathVariable Long id) {
    return service.findById(id)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		Optional<Usuario> usuario = service.findById(id);
		if (usuario.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		service.deleteById(id);
	}
}
