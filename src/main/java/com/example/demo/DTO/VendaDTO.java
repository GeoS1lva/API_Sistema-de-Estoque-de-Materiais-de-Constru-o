/*
 * Geovana Paula da Silva RA 170610-2024
*/

package com.example.demo.DTO;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class VendaDTO {

    @NotBlank(message = "Insira o Id do Cliente!")
    private Long clienteId;

    @NotBlank(message = "Insira o Id do Produto!")
    private Long produtoId;

    @NotBlank(message = "Insira a quantidade comprada!")
    private int quantidade;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime dataVenda;

    public VendaDTO(Long clienteId, Long produtoId, int quantidade){
        this.clienteId = clienteId;
        this.produtoId = produtoId;
        this.quantidade = quantidade;
        dataVenda = LocalDateTime.now();
    }
}
