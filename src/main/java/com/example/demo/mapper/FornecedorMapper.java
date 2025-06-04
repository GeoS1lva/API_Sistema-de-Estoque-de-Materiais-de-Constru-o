package com.example.demo.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.example.demo.DTO.FornecedorDTO;
import com.example.demo.domain.Fornecedor;

@Mapper(componentModel = "spring")
public interface FornecedorMapper {

    FornecedorDTO toDto(Fornecedor fornecedor);

    Fornecedor toEntity(FornecedorDTO fornecedorDTO);

    List<FornecedorDTO> toDtoList(List<Fornecedor> fornecedores);

    List<Fornecedor> toEntityList(List<FornecedorDTO> fornecedoresDTO);

}
