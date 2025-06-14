/*---------------------
Autor: Eduardo Bernardes Zanin
---------------------*/

package com.example.demo.domain;

import java.util.List;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "categoria")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class Categoria extends Entidade {

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String descricao;

    @OneToMany(mappedBy = "categoria")
    private List<Produto> produtos;
}