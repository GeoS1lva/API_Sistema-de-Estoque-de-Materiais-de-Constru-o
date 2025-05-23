/*
 * Geovana Paula da Silva RA 170610-2024
*/

package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Venda;

@Repository
public interface IVendaRepository extends JpaRepository<Venda, Long> { 
    Venda getById(Long id);

    void deleteById(Long Id);
}
