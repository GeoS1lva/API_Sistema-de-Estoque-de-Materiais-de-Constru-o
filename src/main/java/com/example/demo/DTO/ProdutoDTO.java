package com.example.demo.DTO;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProdutoDTO {

    @NotBlank(message = "Insira o nome do produto!")
    @Column(length = 100)
    @Schema(example = "Nome do produto")
    private String nome;

    @NotBlank(message = "Insira a descrição do produto!")
    @Column(length = 255)
    @Schema(example = "Descrição do produto")
    private String descricao;

    @NotNull(message = "Insira a quantidade do produto!")
    @Schema(example = "50")
    private Integer quantidade;

    @NotNull(message = "Insira o preço unitário do produto!")
    @Schema(example = "99.99")
    private BigDecimal precoUnitario;

    @NotNull(message = "Insira a categoria do produto!")
    @Schema(example = "1")
    private Long categoriaId;
    
}
