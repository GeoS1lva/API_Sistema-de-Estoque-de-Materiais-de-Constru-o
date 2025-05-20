package com.example.demo.DTO;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CategoriaDTO {

    @NotBlank(message = "Insira o nome da categoria!")
    @Column(nullable = false, unique = true, length = 100)
    private String nome;

    @NotBlank(message = "Insira a descrição da categoria!")
    @Column(nullable = false, length = 255)
    @Size(max = 255, message = "A descrição deve ter no máximo 255 caracteres")
    private String descricao;
    
}
