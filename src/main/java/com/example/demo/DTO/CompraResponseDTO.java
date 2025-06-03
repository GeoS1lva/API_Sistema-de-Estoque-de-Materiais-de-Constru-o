/*
 * Geovana Paula da Silva RA 170610-2024
*/

package com.example.demo.DTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CompraResponseDTO {

    private Long produtoId;

    private Long fornecedorId;

    private Integer quantidade;

    private BigDecimal valorTotal;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime dataCompra;
}
