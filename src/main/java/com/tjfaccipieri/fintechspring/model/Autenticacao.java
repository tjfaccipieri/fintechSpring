package com.tjfaccipieri.fintechspring.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tb_autenticacao")
public class Autenticacao {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_autenticacao")
  private Long id;
  
  @NotNull
  @Size(max = 60)
  @Column(length = 60, nullable = false, unique = true)
  private String email;
  
  @NotNull
  @Size(min = 8,max = 50)
  @Column(length = 50, nullable = false)
  private String senha;
  
  @OneToOne
  @JoinColumn(name = "id_usuario", nullable = false)
  private Usuario usuario;
  
  public Long getId() {
    return id;
  }
  
  public void setId(Long id) {
    this.id = id;
  }
  
  public String getEmail() {
    return email;
  }
  
  public void setEmail(String email) {
    this.email = email;
  }
  
  public String getSenha() {
    return senha;
  }
  
  public void setSenha(String senha) {
    this.senha = senha;
  }
  
  public Usuario getUsuario() {
    return usuario;
  }
  
  public void setUsuario(Usuario usuario) {
    this.usuario = usuario;
  }
}
