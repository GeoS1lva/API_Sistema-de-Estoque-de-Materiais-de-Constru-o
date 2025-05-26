package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.domain.Produto;

public interface IProdutoRepository extends JpaRepository<Produto, Long> {
    boolean existsByNome(String nome);

    boolean existsByDescricao(String descricao);

    boolean existsById(Long id);

    void deleteById(Long id);
}
