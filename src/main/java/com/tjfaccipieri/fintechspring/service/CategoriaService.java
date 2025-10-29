package com.tjfaccipieri.fintechspring.service;

import com.tjfaccipieri.fintechspring.dto.categoria.CategoriaCreateDTO;
import com.tjfaccipieri.fintechspring.dto.categoria.CategoriaUpdateDTO;
import com.tjfaccipieri.fintechspring.model.Categoria;
import com.tjfaccipieri.fintechspring.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {
  @Autowired
  private CategoriaRepository repository;
  
  public List<Categoria> findAll() {
    return repository.findAll();
  }
  
  public Optional<Categoria> findById(Long id) {
    Optional<Categoria> categoria = repository.findById(id);
    if (categoria.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Categoria não encontrada.", null);
    }
    return categoria;
  }
  
  public Categoria create(CategoriaCreateDTO categoriaCreateDTO) {
    Categoria categoria = new Categoria();
    categoria.setNome(categoriaCreateDTO.nome());
    return repository.save(categoria);
  }
  
  public Categoria update(CategoriaUpdateDTO categoriaUpdateDTO) {
    Categoria categoria = findById(categoriaUpdateDTO.id()).get();
    categoria.setNome(categoriaUpdateDTO.nome());
    return repository.save(categoria);
  }
  
  public void delete(Long id) {
    findById(id).ifPresent(repository::delete);
  }
  
}
