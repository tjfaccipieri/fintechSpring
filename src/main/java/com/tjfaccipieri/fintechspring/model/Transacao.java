package com.tjfaccipieri.fintechspring.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "tb_transacao")
public class Transacao {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  
  @NotNull
  @Size(min = 3, max = 80)
  @Column(nullable = false, length = 80)
  private String descricao;
  
  @UpdateTimestamp
  private LocalDateTime data;
  
  @Column(precision = 19, scale = 4)
  private BigDecimal valor = BigDecimal.ZERO;
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "id_conta", nullable = false)
  @JsonBackReference("conta-transacao")
  private Conta conta;
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "id_categoria", nullable = false)
  private Categoria categoria;
  
  @ManyToOne
  @JoinColumn(name = "id_cartao")
  @JsonBackReference("cartao-transacao")
  private Cartao cartao;
  
  @Enumerated(EnumType.STRING)
  @Column(name = "tipo_transacao", nullable = false)
  private TipoTransacao tipoTransacao;
  
  public Long getId() {
    return id;
  }
  
  public void setId(Long id) {
    this.id = id;
  }
  
  public String getDescricao() {
    return descricao;
  }
  
  public void setDescricao(String descricao) {
    this.descricao = descricao;
  }
  
  public LocalDateTime getData() {
    return data;
  }
  
  public void setData(LocalDateTime data) {
    this.data = data;
  }
  
  public BigDecimal getValor() {
    return valor;
  }
  
  public void setValor(BigDecimal valor) {
    this.valor = valor;
  }
  
  public Conta getConta() {
    return conta;
  }
  
  public void setConta(Conta conta) {
    this.conta = conta;
  }
  
  public Categoria getCategoria() {
    return categoria;
  }
  
  public void setCategoria(Categoria categoria) {
    this.categoria = categoria;
  }
  
  public Cartao getCartao() {
    return cartao;
  }
  
  public void setCartao(Cartao cartao) {
    this.cartao = cartao;
  }
  
  public TipoTransacao getTipoTransacao() {
    return tipoTransacao;
  }
  
  public void setTipoTransacao(TipoTransacao tipoTransacao) {
    this.tipoTransacao = tipoTransacao;
  }
}
