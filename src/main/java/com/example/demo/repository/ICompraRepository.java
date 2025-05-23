/*
 * Geovana Paula da Silva RA 170610-2024
*/

package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Compra;
import java.util.List;


@Repository
public interface ICompraRepository extends JpaRepository<Compra, Long>{
    List<Compra> findByFornecedorId(Long fornecedor_id);

    List<Compra> findByProdutoId(Long produto_id);
}
