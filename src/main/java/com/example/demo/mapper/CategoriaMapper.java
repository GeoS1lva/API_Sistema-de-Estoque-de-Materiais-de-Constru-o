/*---------------------
Autor: Eduardo Bernardes Zanin
---------------------*/

package com.example.demo.mapper;

import java.util.List;
import org.mapstruct.Mapper;
import com.example.demo.DTO.CategoriaDTO;
import com.example.demo.domain.Categoria;

@Mapper(componentModel = "spring")
public interface CategoriaMapper {

    CategoriaDTO tDto(Categoria categoria);

    Categoria tEntity(CategoriaDTO categoriaDTO);

    List<CategoriaDTO> toDtoList(List<Categoria> categorias);

    List<Categoria> toEntityList(List<CategoriaDTO> categoriaDTO);

}
