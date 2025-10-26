package com.tjfaccipieri.fintechspring.controller;

import com.tjfaccipieri.fintechspring.model.UsuarioPJ;
import com.tjfaccipieri.fintechspring.service.UsuarioPJService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuarios/pj")
public class UsuarioPJController {
  @Autowired
  private UsuarioPJService service;
  
  @PostMapping()
  public ResponseEntity<UsuarioPJ> create(@RequestBody UsuarioPJ usuarioPJ) {
    return ResponseEntity.status(HttpStatus.CREATED).body(service.create(usuarioPJ));
  }
  
  @PutMapping()
  public ResponseEntity<UsuarioPJ> update(@RequestBody UsuarioPJ usuarioPJ) {
    return ResponseEntity.status(HttpStatus.OK).body(service.update(usuarioPJ));
  }
}
