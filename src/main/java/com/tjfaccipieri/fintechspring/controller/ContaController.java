package com.tjfaccipieri.fintechspring.controller;

import com.tjfaccipieri.fintechspring.dto.conta.ContaDTO;
import com.tjfaccipieri.fintechspring.dto.conta.ContaUpdateDTO;
import com.tjfaccipieri.fintechspring.model.Conta;
import com.tjfaccipieri.fintechspring.service.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contas")
public class ContaController {
  @Autowired
  private ContaService service;
  
  @GetMapping()
  public ResponseEntity<List<Conta>> findAll() {
    return ResponseEntity.ok(service.findAll());
  }
  
  @GetMapping("/{id}")
  public ResponseEntity<Conta> findById(@PathVariable Long id) {
    return service.findById(id)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }
  
  @PostMapping()
  public ResponseEntity<Conta> create(@RequestBody ContaDTO conta) {
    return ResponseEntity.status(HttpStatus.CREATED).body(service.create(conta));
  }
  
  @PutMapping("/{id}")
  public ResponseEntity<Conta> update(@PathVariable Long id, @RequestBody ContaUpdateDTO conta) {
    return ResponseEntity.ok(service.update(id, conta));
  }
  
  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable Long id) {
    service.delete(id);
  }
}
