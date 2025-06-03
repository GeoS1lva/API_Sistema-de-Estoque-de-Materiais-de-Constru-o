/*
 * Geovana Paula da Silva RA 170610-2024
*/

package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Cliente;

import jakarta.transaction.Transactional;


@Repository
@Transactional
public interface IClienteRepository extends JpaRepository<Cliente, Long>{
    boolean existsByCpf(String cpf);

    void deleteByCpf(String cpf);

    Cliente getById(Long id);

    Cliente getByCpf(String cpf);
}