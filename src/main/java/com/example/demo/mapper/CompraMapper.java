/*
 * Geovana Paula da Silva RA 170610-2024
*/

package com.example.demo.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.demo.DTO.CompraResponseDTO;
import com.example.demo.domain.Compra;

@Mapper(componentModel = "spring")
public interface CompraMapper {

    @Mapping(source = "produto.id", target = "produtoId")
    @Mapping(source = "fornecedor.id", target = "fornecedorId")
    CompraResponseDTO toDTO(Compra compra);
    List<CompraResponseDTO> toDTOList(List<Compra> compras);
}
