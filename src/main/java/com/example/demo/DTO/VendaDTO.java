/*
 * Geovana Paula da Silva RA 170610-2024
*/

package com.example.demo.DTO;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class VendaDTO {

    @NotNull(message = "Insira o Id do Cliente!")
    private Long clienteId;

    @NotNull(message = "Insira o Id do Produto!")
    private Long produtoId;

    @NotNull(message = "Insira a quantidade comprada!")
    private Integer quantidade;
}
