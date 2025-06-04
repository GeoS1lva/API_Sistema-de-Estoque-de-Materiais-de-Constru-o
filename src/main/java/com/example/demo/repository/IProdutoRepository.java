package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Produto;

@Repository
public interface IProdutoRepository extends JpaRepository<Produto, Long> {
    boolean existsByNome(String nome);

    boolean existsByDescricao(String descricao);

    boolean existsById(Long id);

    void deleteById(Long id);

    Produto getById(Long id);
}
