package com.tjfaccipieri.fintechspring.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
@Table(name = "tb_tipo_transacao")
public class TipoTransacao {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  
  @NotNull
  @Size(min = 3, max = 80)
  @Column(nullable = false, length = 80)
  private String nome;
  
  @OneToMany(mappedBy = "tipoTransacao", cascade = CascadeType.ALL)
  private List<Transacao> transacao;
  
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
  
  public List<Transacao> getTransacao() {
    return transacao;
  }
  
  public void setTransacao(List<Transacao> transacao) {
    this.transacao = transacao;
  }
}
