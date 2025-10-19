package com.tjfaccipieri.fintechspring.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "tb_conta")
public class Conta {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  
  private String nome;
  
  @Column(precision = 19, scale = 4)
  private BigDecimal saldo = BigDecimal.ZERO;
  
  @ManyToOne
  @JoinColumn(name = "id_usuario", nullable = false)
  private Usuario usuario;
  
  @OneToMany(mappedBy = "conta", cascade = CascadeType.ALL, orphanRemoval = true)
  @JsonManagedReference("conta-cartao")
  private List<Cartao> cartoes;
  
  @OneToMany(mappedBy = "conta",  cascade = CascadeType.ALL, orphanRemoval = true)
  @JsonManagedReference("conta-transacao")
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
  
  public BigDecimal getSaldo() {
    return saldo;
  }
  
  public void setSaldo(BigDecimal saldo) {
    this.saldo = saldo;
  }
  
  public Usuario getUsuario() {
    return usuario;
  }
  
  public void setUsuario(Usuario usuario) {
    this.usuario = usuario;
  }
  
  public List<Cartao> getCartoes() {
    return cartoes;
  }
  
  public void setCartoes(List<Cartao> cartoes) {
    this.cartoes = cartoes;
  }
  
  public List<Transacao> getTransacoes() {
    return transacoes;
  }
  
  public void setTransacoes(List<Transacao> transacoes) {
    this.transacoes = transacoes;
  }
}
