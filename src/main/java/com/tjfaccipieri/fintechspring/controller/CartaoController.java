package com.tjfaccipieri.fintechspring.controller;

import com.tjfaccipieri.fintechspring.dto.cartao.CartaoCreateDTO;
import com.tjfaccipieri.fintechspring.dto.cartao.CartaoResponseDTO;
import com.tjfaccipieri.fintechspring.dto.cartao.CartaoUpdateDTO;
import com.tjfaccipieri.fintechspring.model.Cartao;
import com.tjfaccipieri.fintechspring.service.CartaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cartoes")
public class CartaoController {
  @Autowired
  private CartaoService service;
  
  @GetMapping()
  public ResponseEntity<List<CartaoResponseDTO>> getAll() {
    return ResponseEntity.ok(service.findAll());
  }
  
  @GetMapping("/{id}")
  public ResponseEntity<Cartao> getById(@PathVariable Long id) {
    return service.findById(id)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }
  
  @PostMapping()
  public ResponseEntity<CartaoResponseDTO> create(@RequestBody CartaoCreateDTO cartao) {
    return ResponseEntity.status(HttpStatus.CREATED).body(service.create(cartao));
  }
  
  @PutMapping("/{id}")
  public ResponseEntity<CartaoResponseDTO> update(@PathVariable Long id, @RequestBody CartaoUpdateDTO cartao) {
    return ResponseEntity.ok(service.update(id, cartao));
  }
  
  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable Long id) {
    service.delete(id);
  }
}
