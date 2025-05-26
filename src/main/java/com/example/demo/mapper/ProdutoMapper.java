/*---------------------
Autor: Eduardo Bernardes Zanin
---------------------*/

package com.example.demo.mapper;

import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import com.example.demo.DTO.ProdutoDTO;
import com.example.demo.domain.Produto;

@Mapper(componentModel = "spring")
public interface ProdutoMapper {

    @Mapping(target = "categoriaId", source = "categoria.id")
    ProdutoDTO toDTO(Produto produto);

    @Mapping(target = "categoria.id", ignore = true)
    Produto toEntity(ProdutoDTO produtoDTO);

    List<ProdutoDTO> toDTOList(List<Produto> produtos);

    List<Produto> toEntityList(List<ProdutoDTO> produtosDTO);

}
