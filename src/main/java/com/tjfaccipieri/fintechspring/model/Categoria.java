package com.tjfaccipieri.fintechspring.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
@Table(name = "tb_categoria")
public class Categoria {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  
  @NotNull
  @Size(min = 3, max = 20)
  @Column(nullable = false, length = 20)
  private String nome;
  
  @OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL)
  @JsonManagedReference("categoria-transacao")
  private List<Transacao> transacoes;
  
  public Long getId() {
    return id;
  }
  
  public void setId(Long id) {
    this.id = id;
  }
  
  public String getNome() {
    return nome;
  }
  
  public void setNome(String nome) {
    this.nome = nome;
  }
  
  public List<Transacao> getTransacoes() {
    return transacoes;
  }
  
  public void setTransacoes(List<Transacao> transacoes) {
    this.transacoes = transacoes;
  }
}
