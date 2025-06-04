/*---------------------
Autor: Eduardo Bernardes Zanin
---------------------*/

package com.example.demo.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Categoria;

@Repository
public interface ICategoriaRepository extends JpaRepository<Categoria, Long> {
    boolean existsByNome(String nome);

    boolean existsById(Long id);

    Optional<Categoria> findByNome(String nome);

    void deleteById(Long id);

}
