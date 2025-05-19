package com.example.demo.domain;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome do produto não pode ser vazio.")
    @Column(nullable = false, length = 100)
    private String nome;

    @NotBlank(message = "A descrição do produto não pode ser vazia.")
    @Column(nullable = false, length = 255)
    private String descricao;
    
    @NotNull(message = "A quantidade não pode ser nula.")
    @Column(nullable = false)
    private Integer quantidade;

    @NotNull(message = "O preço unitário não pode ser nulo.")
    @Column(nullable = false)
    private BigDecimal precoUnitario;
    
    


    @ManyToOne
    @JoinColumn(name = "categoria_id" , nullable = false)
    private Categoria Categoria;
}