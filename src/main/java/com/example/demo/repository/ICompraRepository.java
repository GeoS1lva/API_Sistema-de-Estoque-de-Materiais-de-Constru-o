/*
 * Geovana Paula da Silva RA 170610-2024
*/

package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Compra;
import com.example.demo.domain.Fornecedor;
import com.example.demo.domain.Produto;

import jakarta.transaction.Transactional;


@Repository
@Transactional
public interface ICompraRepository extends JpaRepository<Compra, Long>{
    Compra getById(Long Id);

    boolean existsByFornecedor(Fornecedor fornecedor);

    boolean existsByProduto(Produto produto);
}
