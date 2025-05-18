package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Cliente;
import java.util.Optional;

@Repository
public interface IClienteRepository extends JpaRepository<Cliente, Long>{
    Optional<Cliente> findBycpf(String cpf);

    void deleteByCpf(String cpf);
}