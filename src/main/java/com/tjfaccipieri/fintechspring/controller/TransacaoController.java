package com.tjfaccipieri.fintechspring.controller;

import com.tjfaccipieri.fintechspring.dto.transacao.TransacaoCreateDTO;
import com.tjfaccipieri.fintechspring.dto.transacao.TransacaoUpdateDTO;
import com.tjfaccipieri.fintechspring.model.Transacao;
import com.tjfaccipieri.fintechspring.service.TransacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transacoes")
public class TransacaoController {
  @Autowired
  private TransacaoService service;
  
  @GetMapping()
  public ResponseEntity<List<Transacao>> getAll() {
    return ResponseEntity.ok(service.getAll());
  }
  
  @GetMapping("/{id}")
  public ResponseEntity<Transacao> getById(@PathVariable Long id) {
    return service.findById(id)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }
  
  @PostMapping()
  public ResponseEntity<Transacao> create(@RequestBody TransacaoCreateDTO transacao) {
    return ResponseEntity.status(HttpStatus.CREATED).body(service.create(transacao));
  }
  
  @PutMapping("/{id}")
  public ResponseEntity<Transacao> update(@PathVariable Long id, @RequestBody TransacaoUpdateDTO transacao) {
    return ResponseEntity.ok(service.update(id, transacao));
  }
  
  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable Long id) {
    service.delete(id);
  }
}
