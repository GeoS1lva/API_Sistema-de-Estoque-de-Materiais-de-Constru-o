/*---------------------
Autor: Eduardo Bernardes Zanin
---------------------*/

package com.example.demo.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class FornecedorDTO {

    @NotBlank(message = "Insira o nome do fornecedor!")
    @Column(length = 100)
    @Schema(example = "Nome do fornecedor")
    private String nome;

    @NotBlank(message = "Insira o CNPJ do fornecedor!")
    @Column(length = 14)
    @Size(max = 14, message = "O CNPJ deve ter no máximo 14 Digitos")
    @Schema(example = "12345678000195")
    private String cnpj;

    @NotBlank(message = "Insira o telefone do fornecedor!")
    @Column(length = 12)
    @Size(max = 12, message = "O telefone deve ter no máximo 12 Digitos")
    @Schema(example = "11987654321")
    private String telefone;

    @NotBlank(message = "Insira o email do fornecedor!")
    @Column(length = 100)
    @Size(max = 100, message = "O email deve ter no máximo 100 caracteres")
    @Schema(example = "fornecedor95@gmail.com")
    private String email;

}
