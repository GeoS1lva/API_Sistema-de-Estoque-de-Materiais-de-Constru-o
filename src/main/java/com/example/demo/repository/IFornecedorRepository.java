package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.domain.Fornecedor;

public interface IFornecedorRepository extends JpaRepository<Fornecedor, Long> {
    Fornecedor getById(Long id);

    boolean existsById(Long id);
}
