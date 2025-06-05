/*
 * Geovana Paula da Silva RA 170610-2024
*/

package com.example.demo.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="compras")
@Getter @Setter
@NoArgsConstructor
public class Compra extends Entidade{
    
    @ManyToOne
    @JoinColumn(name = "produto_id", nullable = false)
    private Produto produto;

    @ManyToOne
    @JoinColumn(name = "fornecedor_id", nullable = false)
    private Fornecedor fornecedor;

    @Column(nullable = false)
    private Integer quantidade;

    @Column(nullable = false)
    private BigDecimal valorTotal;

    @Column(nullable = false)
    private LocalDateTime dataCompra;

    public Compra(Produto produto, Fornecedor fornecedor, Integer quantidade){
        this.produto = produto;
        this.fornecedor = fornecedor;
        this.quantidade = quantidade;
        valorTotal = produto.getPrecoUnitario().multiply(BigDecimal.valueOf(quantidade));
        this.dataCompra = LocalDateTime.now();
    }
}
