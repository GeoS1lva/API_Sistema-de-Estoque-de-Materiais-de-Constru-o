/*
 * Geovana Paula da Silva RA 170610-2024
*/

package com.example.demo.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteDTO {

    @NotBlank(message = "Insira o nome!")
    @Column(length = 100)
    @Schema(example = "Nome completo do cliente")
    private String nome;

    @NotBlank(message = "Insira o CPF!")
    @Size(min = 11, max = 11, message = "O CPF deve conter 11 caracteres")
    @Schema(example = "CPF do cliente (somente números)")
    private String cpf;

    @NotBlank(message = "Insira o Telefone")
    @Size(min = 11, max = 11, message = "O número deve conter DDD e o 9")
    @Schema(example = "Telefone de contato")
    private String telefone;
}
