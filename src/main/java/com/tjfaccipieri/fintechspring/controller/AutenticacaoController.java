package com.tjfaccipieri.fintechspring.controller;

import com.tjfaccipieri.fintechspring.dto.AutenticacaoDTO;
import com.tjfaccipieri.fintechspring.model.Autenticacao;
import com.tjfaccipieri.fintechspring.service.AutenticacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AutenticacaoController {
  @Autowired
  private AutenticacaoService service;
  /*
  @PostMapping("/create")
  public ResponseEntity<Autenticacao> autenticar(@RequestBody AutenticacaoDTO autenticacao) {
    return ResponseEntity.status(HttpStatus.CREATED).body(service.create(autenticacao));
  }
  
  
  * TODO: Verificar se pode colocar uma criptografia e camada de segurança no projeto,
  *  e implementar numa V2 do projeto.
  *
  @PostMapping("/login")
  public ResponseEntity<Autenticacao> login(@RequestBody Autenticacao autenticacao) {
    return ResponseEntity.status(HttpStatus.OK).body(service.login(autenticacao));
  }
  */
}
