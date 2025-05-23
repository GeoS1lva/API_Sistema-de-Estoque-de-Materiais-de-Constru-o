package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.domain.Categoria;

public interface ICategoriaRepository extends JpaRepository<Categoria, Long> {
    boolean existsByNome(String nome);

    boolean existsById(Long id);

    Optional<Categoria> findByNome(String nome);

}
