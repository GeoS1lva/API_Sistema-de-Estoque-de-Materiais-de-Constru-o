package com.example.demo.service;

import java.util.List;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.DTO.ClienteDTO;
import com.example.demo.domain.Cliente;
import com.example.demo.mapper.ClienteMapper;
import com.example.demo.repository.IClienteRepository;

@Service
public class ClienteService {
    
    @Autowired
    private IClienteRepository clienteRepository;

    @Autowired
    private ClienteMapper clienteMapper;

    public ClienteDTO cadastrarCliente(ClienteDTO cliente){
        Cliente novoCliente = clienteMapper.toEntity(cliente);
        return clienteMapper.toDTO(clienteRepository.save(novoCliente));
    }

    public Resultado listarClientes(){
        List<Cliente> clientes = clienteRepository.findAll();

        if(clientes.isEmpty()){
            return Resultado.erro("Não possui clientes cadastrado!");
        }

        return Resultado.sucesso(clienteMapper.toDTOList(clientes));
    }

    public Resultado deletarPorCpf(String cpf){
        Optional<Cliente> cliente = clienteRepository.findBycpf(cpf);

        if (cliente.isEmpty()){
            return Resultado.erro("Cliente não encontrado!");
        }

        clienteRepository.deleteByCpf(cpf);
        return Resultado.sucesso("Cliente excluído com Sucesso!");
    }
}
