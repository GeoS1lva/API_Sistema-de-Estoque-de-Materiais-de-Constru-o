/*---------------------
Autor: Eduardo Bernardes Zanin
---------------------*/

package com.example.demo.DTO;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CategoriaDTO {

    @NotBlank(message = "Insira o nome da categoria!")
    @Column(nullable = false, unique = true, length = 30)
    private String nome;

    @NotBlank(message = "Insira a descrição da categoria!")
    @Column(nullable = false, length = 125)
    @Size(max = 125, message = "A descrição deve ter no máximo 125 caracteres")
    private String descricao;

}
