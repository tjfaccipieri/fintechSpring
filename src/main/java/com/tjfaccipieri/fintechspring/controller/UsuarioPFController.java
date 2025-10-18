package com.tjfaccipieri.fintechspring.controller;

import com.tjfaccipieri.fintechspring.model.UsuarioPF;
import com.tjfaccipieri.fintechspring.service.UsuarioPFService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuarios/pf")
public class UsuarioPFController {
	@Autowired
	private UsuarioPFService service;

	//@GetMapping("/{id}")
	//public UsuarioPF findById(@PathVariable String id) {}

	@PostMapping()
	public ResponseEntity<UsuarioPF> create(@RequestBody UsuarioPF usuarioPF) {
		return ResponseEntity.status(HttpStatus.CREATED).body(service.create(usuarioPF));
	}
}
