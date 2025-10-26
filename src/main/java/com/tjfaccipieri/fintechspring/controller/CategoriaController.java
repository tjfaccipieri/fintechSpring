package com.tjfaccipieri.fintechspring.controller;

import com.tjfaccipieri.fintechspring.dto.categoria.CategoriaCreateDTO;
import com.tjfaccipieri.fintechspring.model.Categoria;
import com.tjfaccipieri.fintechspring.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {
  @Autowired
  private CategoriaService service;
  
  @GetMapping()
  public ResponseEntity<List<Categoria>> findAll() {
    return ResponseEntity.ok(service.findAll());
  }
  
  @GetMapping("/{id}")
  public ResponseEntity<Categoria> findById(@PathVariable Long id) {
    return service.findById(id)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }
  
  @PostMapping()
  public ResponseEntity<Categoria> create(@RequestBody CategoriaCreateDTO categoriaCreateDTO) {
    return ResponseEntity.status(HttpStatus.CREATED).body(service.create(categoriaCreateDTO));
  }
  
  @PutMapping()
  public ResponseEntity<Categoria> update(@RequestBody Categoria categoria) {
    return ResponseEntity.ok(service.update(categoria));
  }
  
  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable Long id) {
    service.delete(id);
  }
}
