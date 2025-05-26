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
@Table(name="vendas")
@Getter @Setter
@NoArgsConstructor
public class Venda extends Entidade{

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "produto_id", nullable = false)
    private Produto produto;

    @Column(nullable = false)
    private int quantidade;

    @Column(nullable = false)
    private BigDecimal valorTotal;

    @Column(nullable = false)
    private LocalDateTime dataVenda;

    public Venda (Cliente cliente, Produto produto, int quantidade, LocalDateTime dataVenda){
        this.cliente = cliente;
        this.produto = produto;
        this.quantidade = quantidade;
        valorTotal = produto.getPrecoUnitario().multiply(BigDecimal.valueOf(quantidade));
        this.dataVenda = dataVenda;
    }
}
