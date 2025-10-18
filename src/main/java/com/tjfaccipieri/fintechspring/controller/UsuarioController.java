package com.tjfaccipieri.fintechspring.controller;

import com.tjfaccipieri.fintechspring.model.Usuario;
import com.tjfaccipieri.fintechspring.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/usuarios")
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
}
