/*---------------------
Autor: Eduardo Bernardes Zanin
---------------------*/

package com.example.demo.service;

import java.math.BigDecimal;
import java.util.Optional;
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

        if (produtoDTO.getNome() == null || " ".equals(produtoDTO.getNome())) {
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

        Produto produto = new Produto();
        produto.setNome(produtoDTO.getNome());
        produto.setDescricao(produtoDTO.getDescricao());
        produto.setPrecoUnitario(produtoDTO.getPrecoUnitario());
        produto.setQuantidade(produtoDTO.getQuantidade());
        produto.setCategoria(categoria);

        produtoRepository.save(produto);
        return Resultado.sucesso("Produto cadastrado com sucesso com o ID: " + produto.getId() + "!");
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
            ProdutoDTO produtoDTO = produtoMapper.toDTO(produto);
            return Resultado.sucesso(produtoDTO);
        }
    }

    @Transactional
    public Resultado atualizarProduto(@Valid Long id, ProdutoDTO produtoDTO) {
        if (produtoDTO.getNome() == null || "".equals(produtoDTO.getNome())) {
            return Resultado.erro("O nome do produto não pode ser vazio!");
        }

        if (produtoDTO.getDescricao() == null || "".equals(produtoDTO.getDescricao())) {
            return Resultado.erro("A descrição do produto não pode ser vazia!");
        }

        if (produtoDTO.getPrecoUnitario() == null || produtoDTO.getPrecoUnitario().compareTo(BigDecimal.ZERO) <= 0) {
            return Resultado.erro("O preço unitário deve ser maior que zero.");
        }

        if (produtoDTO.getQuantidade() == null || produtoDTO.getQuantidade() <= 0) {
            return Resultado.erro("A quantidade deve ser maior que zero.");
        }

        Optional<Produto> optionalProduto = produtoRepository.findById(id);
        if (optionalProduto.isPresent()) {
            Produto produto = optionalProduto.get();
            produto.setNome(produtoDTO.getNome());
            produto.setDescricao(produtoDTO.getDescricao());
            produto.setQuantidade(produtoDTO.getQuantidade());
            produto.setPrecoUnitario(produtoDTO.getPrecoUnitario());
            produtoRepository.save(produto);
            return Resultado.sucesso(produtoMapper.toDTO(produto));
        }
        return Resultado.erro("Produto não encontrado!");
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
