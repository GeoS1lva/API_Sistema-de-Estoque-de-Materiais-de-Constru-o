/*---------------------
Autor: Eduardo Bernardes Zanin
---------------------*/

package com.example.demo.DTO;

import java.math.BigDecimal;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ProdutoDTO {

    @Schema(example = "Nome do produto")
    private String nome;

    @NotBlank(message = "Insira a descrição do produto!")
    @Column(length = 255)
    @Size(min = 1, max = 255, message = "A descrição deve ter no máximo 255 caracteres")
    @Schema(example = "Descrição do produto")
    private String descricao;

    @NotNull(message = "Insira a quantidade do produto!")
    @Column(length = 11)
    @Schema(example = "50")
    private Integer quantidade;

    @NotNull(message = "Insira o preço unitário do produto!")
    @Column(precision = 10, scale = 2)
    @Schema(example = "0.00")
    private BigDecimal precoUnitario;

    @NotNull(message = "Insira o ID da categoria do produto!")
    @Schema(example = "1")
    private Long categoriaId;

}
