package com.tjfaccipieri.fintechspring.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
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
  
  @NotNull
  @Size(max = 60)
  @Email
  @Column(length = 60, nullable = false, unique = true)
  private String email;
  
  @NotNull
  @Size(min = 8,max = 50)
  @Column(length = 50, nullable = false)
  private String senha;
  
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
}
