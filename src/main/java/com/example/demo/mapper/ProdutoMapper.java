package com.example.demo.mapper;

import java.util.List;

import org.mapstruct.Mapper;


import com.example.demo.DTO.ProdutoDTO;
import com.example.demo.domain.Produto;

@Mapper(componentModel = "spring")
public interface ProdutoMapper {

     
    ProdutoDTO toDTO(Produto produto);

    Produto toEntity(ProdutoDTO produtoDTO);

    List<ProdutoDTO> toDTOList(List<Produto> produtos);

    List<Produto> toEntityList(List<ProdutoDTO> produtosDTO);

    
}
