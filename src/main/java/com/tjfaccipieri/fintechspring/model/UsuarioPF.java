package com.tjfaccipieri.fintechspring.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CPF;

@Entity
@Table(name = "tb_usuario_pf")
public class UsuarioPF extends Usuario {
  @NotNull
  @CPF(message = "O CPF informado é inválido")
  @Column(length = 11, nullable = false)
  private String cpf;
  
  public String getCpf() {
    return cpf;
  }
  
  public void setCpf(String cpf) {
    this.cpf = cpf;
  }
}
