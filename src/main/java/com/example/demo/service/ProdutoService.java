package com.example.demo.service;


import java.math.BigDecimal;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.DTO.ProdutoDTO;
import com.example.demo.domain.Produto;
import com.example.demo.mapper.ProdutoMapper;
import com.example.demo.repository.IProdutoRepository;

import jakarta.transaction.Transactional;

import org.springframework.util.StringUtils;

@Service
public class ProdutoService {

    @Autowired
    private IProdutoRepository produtoRepository;

    @Autowired
    private ProdutoMapper produtoMapper;

    @Transactional
    public Resultado cadastrarProduto(ProdutoDTO produtoDTO) {
        if(!StringUtils.hasText(produtoDTO.getNome())) {
            return Resultado.erro("O nome do produto não pode ser vazio!");
        }

        if(!StringUtils.hasText(produtoDTO.getDescricao())) {
            return Resultado.erro("A descrição do produto não pode ser vazia!");
        }
        

        if (produtoDTO.getPrecoUnitario() == null || produtoDTO.getPrecoUnitario().compareTo(BigDecimal.ZERO) <= 0) {
            return Resultado.erro("O preço unitário deve ser maior que zero.");
        }

        if (produtoDTO.getQuantidade() == null || produtoDTO.getQuantidade() <= 0) {
            return Resultado.erro("A quantidade deve ser maior que zero.");
        }

         if(produtoRepository.existsByNome(produtoDTO.getNome())){
            return Resultado.erro("Produto já cadastrado!");    
        }
          
        
        Produto produto = produtoMapper.toEntity(produtoDTO);
        Produto savedProduto = produtoRepository.save(produto);
        return Resultado.sucesso(produtoMapper.toDTO(savedProduto));
        }

    public List<ProdutoDTO> listarProdutos() {
        List<Produto> produtos = produtoRepository.findAll();
        if (produtos.isEmpty()) {
            return Collections.emptyList();
        } else {
            return produtos.stream()
                .map(produtoMapper::toDTO)
                .collect(Collectors.toList());
            }
        }
        
    public Resultado detalharProduto(Long id) {
        Produto produto = produtoRepository.findById(id).orElse(null);
        return produto != null ? Resultado.sucesso(produtoMapper.toDTO(produto)) : Resultado.erro("Produto não encontrado!");
    }

    @Transactional
    public Resultado atualizarProduto(Long id, ProdutoDTO produtoDTO) {
        if (!StringUtils.hasText(produtoDTO.getNome())) {
            return Resultado.erro("O nome do produto não pode ser vazio!");
        }

        if (!StringUtils.hasText(produtoDTO.getDescricao())) {
            return Resultado.erro("A descrição do produto não pode ser vazia!");
        }

        if (produtoDTO.getPrecoUnitario() == null || produtoDTO.getPrecoUnitario().compareTo(BigDecimal.ZERO) <= 0) {
            return Resultado.erro("O preço unitário deve ser maior que zero.");
        }

        if (produtoDTO.getQuantidade() == null || produtoDTO.getQuantidade() <= 0) {
            return Resultado.erro("A quantidade deve ser maior que zero.");
        }
        if (produtoRepository.existsByNome(produtoDTO.getNome())) {
            return Resultado.erro("Produto já cadastrado com esse nome!");
        }

            Optional<Produto> optionalProduto = produtoRepository.findById(id);
             if (optionalProduto.isPresent()) {
             Produto produto = optionalProduto.get();
             produto.setNome(produtoDTO.getNome());
             produto.setDescricao(produtoDTO.getDescricao());
             produto.setQuantidade(produtoDTO.getQuantidade());
             produto.setPrecoUnitario(produtoDTO.getPrecoUnitario());
             Produto updatedProduto = produtoRepository.save(produto);
                return Resultado.sucesso("Produto atualizado com sucesso!" + produtoMapper.toDTO(updatedProduto));
            } else {
                return Resultado.erro("Produto não encontrado!");
            }
        }

    @Transactional
    public Resultado removerProduto(Long id) {
        if (!produtoRepository.existsById(id)) {
        return Resultado.erro("Produto não encontrado!");
        } 
        produtoRepository.deleteById(id);
        return Resultado.sucesso("Produto excluído com sucesso!");
    }

}

        

