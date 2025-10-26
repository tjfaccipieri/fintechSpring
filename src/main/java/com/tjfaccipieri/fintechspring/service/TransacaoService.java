package com.tjfaccipieri.fintechspring.service;

import com.tjfaccipieri.fintechspring.dto.transacao.TransacaoCreateDTO;
import com.tjfaccipieri.fintechspring.dto.transacao.TransacaoUpdateDTO;
import com.tjfaccipieri.fintechspring.model.Cartao;
import com.tjfaccipieri.fintechspring.model.Categoria;
import com.tjfaccipieri.fintechspring.model.Conta;
import com.tjfaccipieri.fintechspring.model.TipoTransacao;
import com.tjfaccipieri.fintechspring.model.Transacao;
import com.tjfaccipieri.fintechspring.repository.ContaRepository;
import com.tjfaccipieri.fintechspring.repository.TransacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class TransacaoService {
  @Autowired
  private TransacaoRepository repository;
  
  @Autowired
  private ContaService contaService;

  @Autowired
  private ContaRepository contaRepository;

  @Autowired
  private CategoriaService categoriaService;

  @Autowired
  private CartaoService cartaoService;
  
  public List<Transacao> getAll() {
    return repository.findAll();
  }
  
  public Optional<Transacao> getById(Long id) {
    Optional<Transacao> transacao = repository.findById(id);
    
    if (transacao.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Conta não encontrada.", null);
    }
    
    return transacao;
  }
  
  public Optional<Transacao> findById(Long id) {
    Optional<Transacao> transacao = repository.findById(id);
    
    if (transacao.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Transação não encontrada.", null);
    }
    
    return transacao;
  }
  
  public Transacao create(TransacaoCreateDTO transacaoCreateDTO) {
    Conta conta = contaService.findById(transacaoCreateDTO.idConta())
      .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Conta não encontrada.", null));

    Categoria categoria = categoriaService.findById(transacaoCreateDTO.idCategoria())
      .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Categoria não encontrada.", null));

    Cartao cartao = null;
    if (transacaoCreateDTO.idCartao() != null) {
      cartao = cartaoService.findById(transacaoCreateDTO.idCartao())
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cartão não encontrado.", null));
    }

    Transacao transacao = new Transacao();
    transacao.setDescricao(transacaoCreateDTO.descricao());
    transacao.setValor(transacaoCreateDTO.valor());
    transacao.setConta(conta);
    transacao.setCategoria(categoria);
    transacao.setCartao(cartao);
    transacao.setTipoTransacao(TipoTransacao.valueOf(transacaoCreateDTO.tipoTransacao()));

    BigDecimal novoSaldo = conta.getSaldo();
    switch (transacao.getTipoTransacao()) {
      case RECEITA:
        novoSaldo = novoSaldo.add(transacao.getValor());
        break;
      case DESPESA:
        novoSaldo = novoSaldo.subtract(transacao.getValor());
        break;
    }
    conta.setSaldo(novoSaldo);
    // Save the updated account balance
    contaRepository.save(conta);

    return repository.save(transacao);
  }

  public Transacao update(Long id, TransacaoUpdateDTO transacaoUpdateDTO) {
    Transacao transacao = findById(id)
      .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Transação não encontrada."));

    Conta conta = transacao.getConta();
    BigDecimal valorAntigo = transacao.getValor();
    TipoTransacao tipoAntigo = transacao.getTipoTransacao();

    // Reverter o valor antigo do saldo
    BigDecimal saldo = conta.getSaldo();
    switch (tipoAntigo) {
        case RECEITA:
            saldo = saldo.subtract(valorAntigo);
            break;
        case DESPESA:
            saldo = saldo.add(valorAntigo);
            break;
    }

    transacao.setDescricao(transacaoUpdateDTO.descricao());
    transacao.setValor(transacaoUpdateDTO.valor());
    transacao.setTipoTransacao(TipoTransacao.valueOf(transacaoUpdateDTO.tipoTransacao()));

    if (transacaoUpdateDTO.idCategoria() != null) {
        Categoria categoria = categoriaService.findById(transacaoUpdateDTO.idCategoria())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Categoria não encontrada."));
        transacao.setCategoria(categoria);
    }

    if (transacaoUpdateDTO.idCartao() != null) {
        Cartao cartao = cartaoService.findById(transacaoUpdateDTO.idCartao())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cartão não encontrado."));
        transacao.setCartao(cartao);
    } else {
        transacao.setCartao(null);
    }

    // Aplicar o novo valor ao saldo
    BigDecimal novoValor = transacao.getValor();
    switch (transacao.getTipoTransacao()) {
        case RECEITA:
            saldo = saldo.add(novoValor);
            break;
        case DESPESA:
            saldo = saldo.subtract(novoValor);
            break;
    }
    conta.setSaldo(saldo);

    contaRepository.save(conta);
    return repository.save(transacao);
  }
  
  public void delete(Long id) {
    Transacao transacao = findById(id)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Transação não encontrada."));
    
    Conta conta = transacao.getConta();
    BigDecimal valorAntigo = transacao.getValor();
    TipoTransacao tipoAntigo = transacao.getTipoTransacao();
    
    BigDecimal saldo = conta.getSaldo();
    switch (tipoAntigo) {
      case RECEITA:
        saldo = saldo.subtract(valorAntigo);
        break;
      case DESPESA:
        saldo = saldo.add(valorAntigo);
        break;
    }
    
    conta.setSaldo(saldo);
    contaRepository.save(conta);
    repository.deleteById(id);
  }
}
