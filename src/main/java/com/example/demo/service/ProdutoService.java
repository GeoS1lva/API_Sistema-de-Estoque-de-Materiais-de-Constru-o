/*---------------------
Autor: Eduardo Bernardes Zanin
---------------------*/

package com.example.demo.service;

import java.math.BigDecimal;
import java.util.stream.Collectors;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.DTO.ProdutoDTO;
import com.example.demo.domain.Categoria;
import com.example.demo.domain.Produto;
import com.example.demo.mapper.ProdutoMapper;
import com.example.demo.repository.ICategoriaRepository;
import com.example.demo.repository.IProdutoRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Service
public class ProdutoService {

    @Autowired
    private IProdutoRepository produtoRepository;

    @Autowired
    private ProdutoMapper produtoMapper;

    @Autowired
    private ICategoriaRepository categoriaRepository;

    @Transactional
    public Resultado cadastrarProduto(@Valid ProdutoDTO produtoDTO) {

        Categoria categoria = categoriaRepository.findById(produtoDTO.getCategoriaId()).orElse(null);
        if (categoria == null) {
            return Resultado.erro("Categoria não encontrada!");
        }

        if (produtoDTO.getNome() == null || produtoDTO.getNome().isEmpty()) {
            return Resultado.erro("O nome do produto não pode ser vazio!");
        }

        if (produtoDTO.getDescricao() == null || produtoDTO.getDescricao().isEmpty()) {
            return Resultado.erro("A descrição do produto não pode ser vazia!");
        }

        if (produtoDTO.getPrecoUnitario() == null || produtoDTO.getPrecoUnitario().compareTo(BigDecimal.ZERO) <= 0) {
            return Resultado.erro("O preço unitário deve ser maior que zero.");
        }

        if (produtoDTO.getQuantidade() == null || produtoDTO.getQuantidade() <= 0) {
            return Resultado.erro("A quantidade deve ser maior que zero.");
        }

        if (produtoRepository.existsByNome(produtoDTO.getNome())) {
            return Resultado.erro("Produto já cadastrado!");
        }

        Produto novoProduto = produtoMapper.toEntity(produtoDTO);
        novoProduto.setCategoria(categoria);

        produtoRepository.save(novoProduto);
        return Resultado.sucesso("Produto cadastrado com sucesso com o ID: " + novoProduto.getId() + "!");
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

    public Resultado detalharProduto(@Valid Long id) {
        Produto produto = produtoRepository.findById(id).orElse(null);
        if (produto == null) {
            return Resultado.erro("Produto não encontrado!");
        } else {
            return Resultado.sucesso(produtoMapper.toDTO(produto));
        }
    }

    @Transactional
    public Resultado atualizarProduto(@Valid Long id, ProdutoDTO produtoDTO) {
        if (produtoDTO.getNome() == null || produtoDTO.getNome().trim().isEmpty()) {
            return Resultado.erro("O nome do produto não pode ser vazio!");
        }

        if (produtoDTO.getDescricao() == null || produtoDTO.getDescricao().trim().isEmpty()) {
            return Resultado.erro("A descrição do produto não pode ser vazia!");
        }

        if (produtoDTO.getPrecoUnitario() == null || produtoDTO.getPrecoUnitario().compareTo(BigDecimal.ZERO) <= 0) {
            return Resultado.erro("O preço unitário deve ser maior que zero.");
        }

        if (produtoDTO.getQuantidade() == null || produtoDTO.getQuantidade() <= 0) {
            return Resultado.erro("A quantidade deve ser maior que zero.");
        }

        Produto optionalProduto = produtoRepository.getById(id);
        if (optionalProduto == null) {
            return Resultado.erro("Produto nao encontrado!");
        }

        optionalProduto.setNome(produtoDTO.getNome());
        optionalProduto.setDescricao(produtoDTO.getDescricao());
        optionalProduto.setQuantidade(produtoDTO.getQuantidade());
        optionalProduto.setPrecoUnitario(produtoDTO.getPrecoUnitario());
        produtoRepository.save(optionalProduto);
        return Resultado.sucesso(produtoMapper.toDTO(optionalProduto));

    }

    @Transactional
    public Resultado removerProduto(@Valid Long id) {
        if (!produtoRepository.existsById(id)) {
            return Resultado.erro("Produto não encontrado!");
        }
        produtoRepository.deleteById(id);
        return Resultado.sucesso("Produto excluido com sucesso!");
    }

}
