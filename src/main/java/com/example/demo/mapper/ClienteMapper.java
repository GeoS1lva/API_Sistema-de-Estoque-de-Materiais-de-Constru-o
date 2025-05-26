/*
 * Geovana Paula da Silva RA 170610-2024
*/
package com.example.demo.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.example.demo.DTO.ClienteDTO;
import com.example.demo.domain.Cliente;

@Mapper(componentModel = "spring")
public interface ClienteMapper {

    ClienteDTO toDTO(Cliente cliente);
    Cliente toEntity(ClienteDTO clienteDTO);
    List<ClienteDTO> toDTOList(List<Cliente> clientes);
}
