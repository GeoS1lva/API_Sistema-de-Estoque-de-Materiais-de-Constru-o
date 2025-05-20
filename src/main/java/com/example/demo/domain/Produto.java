package com.example.demo.domain;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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

    
    @Column(nullable = false)
    private String nome;

    
    @Column(nullable = false)
    private String descricao;
    
    
    @Column(nullable = false)
    private Integer quantidade;

    
    @Column(nullable = false)
    private BigDecimal precoUnitario;
    
    


    @ManyToOne
    @JoinColumn(name = "categoria_id" , nullable = false)
    private Categoria Categoria;
}