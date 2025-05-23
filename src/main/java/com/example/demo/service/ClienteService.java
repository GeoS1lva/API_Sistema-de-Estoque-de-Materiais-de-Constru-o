/*
 * Geovana Paula da Silva RA 170610-2024
*/

package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.DTO.ClienteDTO;
import com.example.demo.domain.Cliente;
import com.example.demo.mapper.ClienteMapper;
import com.example.demo.repository.IClienteRepository;

import jakarta.transaction.Transactional;

@Service
public class ClienteService {
    
    @Autowired
    private IClienteRepository clienteRepository;

    @Autowired
    private ClienteMapper clienteMapper;

    public Resultado cadastrarCliente(ClienteDTO cliente){
        if (!cliente.getNome().trim().contains(" ")) {
            return Resultado.erro("Nome deve conter nome e sobrenome");
        }

        if(!validarCpf(cliente.getCpf())) {
            return Resultado.erro("CPF inválido!");
        }

        if(clienteRepository.existsByCpf(cliente.getCpf())){
            return Resultado.erro("CPF já cadastrado!");
        }

        Cliente novoCliente = clienteMapper.toEntity(cliente);

        clienteRepository.save(novoCliente);

        return Resultado.sucesso(clienteMapper.toDTO(novoCliente));
    }

    public Resultado listarClientes(){
        List<Cliente> clientes = clienteRepository.findAll();

        if(clientes.isEmpty()){
            return Resultado.erro("Não possui clientes cadastrado!");
        }

        return Resultado.sucesso(clienteMapper.toDTOList(clientes));
    }

    @Transactional
    public Resultado deletarPorCpf(String cpf){
        if (!clienteRepository.existsByCpf(cpf)){
            return Resultado.erro("Cliente não encontrado!");
        }

        clienteRepository.deleteByCpf(cpf);
        return Resultado.sucesso("Cliente excluído com Sucesso!");
    }

    public static boolean validarCpf(String CPF) {

        String cpf = CPF.replaceAll("\\D", "");

        if (cpf.length() != 11){
            return false;
        }
            
        if (cpf.chars().allMatch(c -> c == cpf.charAt(0))){
            return false;
        }
            
        int soma = 0;
        for (int i = 0; i < 9; i++){
            soma += Character.getNumericValue(cpf.charAt(i)) * (10 - i);
        }
            
        int primeiroDigitoVerificador = 11 - (soma % 11);
        if (primeiroDigitoVerificador >= 10){
            primeiroDigitoVerificador = 0;
        }
            
        soma = 0;
        for (int i = 0; i < 10; i++){
            soma += Character.getNumericValue(cpf.charAt(i)) * (11 - i);
        }

        int segundoDigitoVerificador = 11 - (soma % 11);
        if (segundoDigitoVerificador >= 10){
            segundoDigitoVerificador = 0;
        }
            
        return cpf.charAt(9) == Character.forDigit(primeiroDigitoVerificador, 10) && cpf.charAt(10) == Character.forDigit(segundoDigitoVerificador, 10);
    }
}
