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
import com.example.demo.repository.IVendaRepository;

@Service
public class ClienteService {

    @Autowired
    private IClienteRepository clienteRepository;

    @Autowired
    private IVendaRepository vendaRepository;

    @Autowired
    private ClienteMapper clienteMapper;

    public Resultado cadastrarCliente(ClienteDTO cliente) {
        if(cliente.getNome().isEmpty()){
            return Resultado.erro("Nome não pode ser nulo!");
        }

        if (!cliente.getNome().contains(" ")) {
            return Resultado.erro("Nome deve conter nome e sobrenome");
        }

        if (!validarCpf(cliente.getCpf())) {
            return Resultado.erro("CPF inválido!");
        }

        if(!cliente.getTelefone().matches("[0-9]+")){
            return Resultado.erro("Telefone deve conter somente numeros");
        }

        if(cliente.getTelefone().length() != 11){
            return Resultado.erro("O Numero de telefone precisa ter 11 digitos com DDD");
        }

        if (clienteRepository.existsByCpf(cliente.getCpf())) {
            return Resultado.erro("CPF já cadastrado!");
        }

        Cliente novoCliente = clienteMapper.toEntity(cliente);

        clienteRepository.save(novoCliente);

        return Resultado.sucesso(clienteMapper.toDTO(novoCliente));
    }

    public Resultado listarClientes() {
        List<Cliente> clientes = clienteRepository.findAll();

        if (clientes.isEmpty()) {
            return Resultado.erro("Não possui clientes cadastrado!");
        }

        return Resultado.sucesso(clienteMapper.toDTOList(clientes));
    }

    public Resultado detalharCliente(String cpf){
        if(!validarCpf(cpf)){
            return Resultado.erro("CPF inválido!");
        }
        
        if(!clienteRepository.existsByCpf(cpf)){
            return Resultado.erro("Cliente não encontrado!");
        }

        Cliente cliente = clienteRepository.getByCpf(cpf);
        return Resultado.sucesso(clienteMapper.toDTO(cliente));
    }

    public Resultado deletarPorCpf(String cpf) {
        if (!clienteRepository.existsByCpf(cpf)) {
            return Resultado.erro("Cliente não encontrado!");
        }

        Cliente cliente = clienteRepository.getByCpf(cpf);

        if (vendaRepository.existsByCliente(cliente)) {
            return Resultado.erro("Esse cliente não pode ser excluído pois possui vendas registradas!");
        }

        clienteRepository.deleteByCpf(cpf);
        return Resultado.sucesso("Cliente excluído com Sucesso!");
    }

    public static boolean validarCpf(String CPF) {
        String cpf = CPF.replaceAll("\\D", "");

        if (cpf.length() != 11) {
            return false;
        }

        if (cpf.chars().allMatch(c -> c == cpf.charAt(0))) {
            return false;
        }

        int soma = 0;
        for (int i = 0; i < 9; i++) {
            soma += Character.getNumericValue(cpf.charAt(i)) * (10 - i);
        }

        int primeiroDigitoVerificador = 11 - (soma % 11);
        if (primeiroDigitoVerificador >= 10) {
            primeiroDigitoVerificador = 0;
        }

        soma = 0;
        for (int i = 0; i < 10; i++) {
            soma += Character.getNumericValue(cpf.charAt(i)) * (11 - i);
        }

        int segundoDigitoVerificador = 11 - (soma % 11);
        if (segundoDigitoVerificador >= 10) {
            segundoDigitoVerificador = 0;
        }

        return cpf.charAt(9) == Character.forDigit(primeiroDigitoVerificador, 10)
                && cpf.charAt(10) == Character.forDigit(segundoDigitoVerificador, 10);
    }
}
