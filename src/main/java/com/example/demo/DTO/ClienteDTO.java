/*
 * Geovana Paula da Silva RA 170610-2024
*/

package com.example.demo.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteDTO {

    @Schema(example = "Nome completo do cliente")
    private String nome;

    @Schema(example = "CPF do cliente (somente números)")
    private String cpf;

    @Schema(example = "Telefone de contato")
    private String telefone;
}
