package com.example.demo.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.demo.DTO.VendaResponseDTO;
import com.example.demo.domain.Venda;

@Mapper(componentModel = "spring")
public interface VendaMapper {

    @Mapping(source = "cliente.id", target = "clienteId")
    @Mapping(source = "produto.id", target = "produtoId")
    VendaResponseDTO toDTO(Venda venda);
    List<VendaResponseDTO> toDTOList(List<Venda> vendas);
}
