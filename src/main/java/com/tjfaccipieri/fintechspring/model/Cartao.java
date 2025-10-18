package com.tjfaccipieri.fintechspring.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
@Table(name = "tb_cartao")
public class Cartao {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  
  @NotNull
  @Size(min = 2, max = 60)
  @Column(nullable = false, length = 60)
  private String nome;
  
  @NotNull
  @Pattern(regexp = "\\d{4}", message = "O número final do cartão deve conter 4 dígitos.")
  @Column(nullable = false, length = 4)
  private String finalCartao;
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name="id_conta", nullable = false)
  private Conta conta;
  
  @OneToMany(mappedBy = "cartao", cascade = CascadeType.ALL)
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
  
  public String getFinalCartao() {
    return finalCartao;
  }
  
  public void setFinalCartao(String finalCartao) {
    this.finalCartao = finalCartao;
  }
  
  public Conta getConta() {
    return conta;
  }
  
  public void setConta(Conta conta) {
    this.conta = conta;
  }
  
  public List<Transacao> getTransacoes() {
    return transacoes;
  }
  
  public void setTransacoes(List<Transacao> transacoes) {
    this.transacoes = transacoes;
  }
}
