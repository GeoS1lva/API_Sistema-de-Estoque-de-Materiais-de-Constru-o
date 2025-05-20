package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.domain.Categoria;

public interface ICategoriaRepository extends JpaRepository<Categoria, Long>  {
    boolean existsByNome(String nome);

    boolean eexistsById(Long id);
  
}
