package com.tjfaccipieri.fintechspring.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tb_usuario")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Usuario {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_usuario")
  private Long id;
  
  @NotNull
  @Size(min = 2, max = 70)
  @Column(nullable = false, length = 70)
  private String nome;
  
  @Column(length = 1500)
  private String foto;
  
  @OneToOne(mappedBy = "usuario",cascade = CascadeType.ALL)
  private Autenticacao autenticacao;
  
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
  
  public String getFoto() {
    return foto;
  }
  
  public void setFoto(String foto) {
    this.foto = foto;
  }
  
  public Autenticacao getAutenticacao() {
    return autenticacao;
  }
  
  public void setAutenticacao(Autenticacao autenticacao) {
    this.autenticacao = autenticacao;
  }
}
