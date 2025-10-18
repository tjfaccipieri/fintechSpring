package com.tjfaccipieri.fintechspring.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CNPJ;

@Entity
@Table(name = "tb_usuario_pj")
public class UsuarioPJ extends Usuario {
  @NotNull
  @CNPJ(message = "O CNPJ informado é inválido")
  @Column(length = 14, nullable = false)
  private String cnpj;
  
  @NotNull
  @Size(min = 2, max = 80)
  @Column(nullable = false, length = 80)
  private String nomeFantasia;
  
  public String getCnpj() {
    return cnpj;
  }
  
  public void setCnpj(String cnpj) {
    this.cnpj = cnpj;
  }
  
  public String getNomeFantasia() {
    return nomeFantasia;
  }
  
  public void setNomeFantasia(String nomeFantasia) {
    this.nomeFantasia = nomeFantasia;
  }
}
