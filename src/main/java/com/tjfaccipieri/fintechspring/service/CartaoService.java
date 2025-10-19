package com.tjfaccipieri.fintechspring.service;

import com.tjfaccipieri.fintechspring.dto.cartao.CartaoCreateDTO;
import com.tjfaccipieri.fintechspring.dto.cartao.CartaoResponseDTO;
import com.tjfaccipieri.fintechspring.dto.cartao.CartaoUpdateDTO;
import com.tjfaccipieri.fintechspring.dto.conta.ContaResponseDTO;
import com.tjfaccipieri.fintechspring.model.Cartao;
import com.tjfaccipieri.fintechspring.model.Conta;
import com.tjfaccipieri.fintechspring.repository.CartaoRepository;
import com.tjfaccipieri.fintechspring.repository.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CartaoService {
  @Autowired
  private CartaoRepository repository;

  @Autowired
  private ContaRepository contaRepository;
  
  public List<CartaoResponseDTO> findAll() {
    /*
    Abordagem mais explícita usando um loop for, caso seja necessário no futuro:

    List<Cartao> cartoes = repository.findAll();
    List<CartaoResponseDTO> dtos = new ArrayList<>();

    for (Cartao cartao : cartoes) {
        dtos.add(toCartaoResponseDTO(cartao));
    }

    return dtos;
    */
    return repository.findAll().stream().map(this::toCartaoResponseDTO).collect(Collectors.toList());
  }

  private CartaoResponseDTO toCartaoResponseDTO(Cartao cartao) {
    ContaResponseDTO contaDTO = new ContaResponseDTO(cartao.getConta().getId(), cartao.getConta().getNome());
    return new CartaoResponseDTO(cartao.getId(), cartao.getNome(), cartao.getFinalCartao(), contaDTO);
  }
  
  public Optional<Cartao> findById(Long id) {
    Optional<Cartao> cartao = repository.findById(id);
    if (cartao.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cartão não encontrado.", null);
    }
    return cartao;
  }
  
  public CartaoResponseDTO create(CartaoCreateDTO cartaoCreateDTO) {
    Conta conta = contaRepository.findById(cartaoCreateDTO.contaId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Conta não encontrada"));
    Cartao newCartao = new Cartao();
    newCartao.setNome(cartaoCreateDTO.nome());
    newCartao.setFinalCartao(cartaoCreateDTO.finalCartao());
    newCartao.setConta(conta);
    Cartao savedCartao = repository.save(newCartao);
    return toCartaoResponseDTO(savedCartao);
  }
  
  public CartaoResponseDTO update(Long id, CartaoUpdateDTO cartaoUpdateDTO) {
    Cartao cartao = findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    cartao.setNome(cartaoUpdateDTO.nome());
    cartao.setFinalCartao(cartaoUpdateDTO.finalCartao());
    Cartao updatedCartao = repository.save(cartao);
    return toCartaoResponseDTO(updatedCartao);
  }
  
  public void delete(Long id) {
    findById(id).ifPresent(repository::delete);
  }
}
