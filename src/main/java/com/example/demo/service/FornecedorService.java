package com.example.demo.service;

import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.DTO.FornecedorDTO;
import com.example.demo.domain.Fornecedor;

import com.example.demo.mapper.FornecedorMapper;
import com.example.demo.repository.IFornecedorRepository;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Service
public class FornecedorService {

    @Autowired
    private IFornecedorRepository fornecedorRepository;

    @Autowired
    private FornecedorMapper fornecedorMapper;

    private boolean ValidarCnpj(String cnpj) {
        cnpj = cnpj.replaceAll("[^0-9]", "");

        if (cnpj.length() != 14) {
            return false;
        }

        if (cnpj.matches("(\\d)\\1{13}")) {
            return false;
        }

        int soma = 0;
        int[] pesos1 = { 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2 };
        for (int i = 0; i < 12; i++) {
            soma += (cnpj.charAt(i) - '0') * pesos1[i];
        }

        int resto = soma % 11;
        int digito1 = (resto < 2) ? 0 : 11 - resto;

        if (digito1 != (cnpj.charAt(12) - '0')) {
            return false;
        }

        soma = 0;
        int[] pesos2 = { 6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2 };
        for (int i = 0; i < 13; i++) {
            soma += (cnpj.charAt(i) - '0') * pesos2[i];
        }

        resto = soma % 11;
        int digito2 = (resto < 2) ? 0 : 11 - resto;

        return digito2 == (cnpj.charAt(13) - '0');
    }
        


    

    private boolean ValidarEmailFornecedor(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    @Transactional
    public Resultado cadastrarFornecedor(FornecedorDTO fornecedorDTO) {

        if (fornecedorDTO.getNome() == null || fornecedorDTO.getNome().trim().isEmpty()) {
            return Resultado.erro("O nome do fornecedor nao pode ser vazio!");
        }

        if (fornecedorDTO.getCnpj() == null || fornecedorDTO.getCnpj().trim().isEmpty()) {
            return Resultado.erro("O CNPJ do fornecedor nao pode ser vazio!");
        }

        if (fornecedorDTO.getTelefone() == null || fornecedorDTO.getTelefone().trim().isEmpty()) {
            return Resultado.erro("O telefone do fornecedor nao pode ser vazio!");
        }

        if (fornecedorDTO.getEmail() == null || fornecedorDTO.getEmail().trim().isEmpty()) {
            return Resultado.erro("O email do fornecedor nao pode ser vazio!");
        }

        if (!ValidarCnpj(fornecedorDTO.getCnpj())) {
            return Resultado.erro("O CNPJ do fornecedor não eh valido!");
        }

        if (!ValidarEmailFornecedor(fornecedorDTO.getEmail())) {
            return Resultado.erro("O email do fornecedor não eh valido!");
        }

        if (fornecedorRepository.existsByCnpj(fornecedorDTO.getCnpj())) {
            return Resultado.erro("Já existe um fornecedor com este CNPJ!");
        }

        Fornecedor fornecedor = fornecedorMapper.toEntity(fornecedorDTO);
        fornecedorRepository.save(fornecedor);

        return Resultado.sucesso("Fornecedor cadastrado com sucesso com ID: " + fornecedor.getId());
    }

    public List<FornecedorDTO> listarFornecedores() {
        List<Fornecedor> fornecedores = fornecedorRepository.findAll();
        if (fornecedores.isEmpty()) {
            return Collections.emptyList();
        } else {
            return fornecedores.stream()
                    .map(fornecedorMapper::toDto)
                    .collect(Collectors.toList());
        }
    }

    public Resultado detalharFornecedor(@Valid Long id) {
        Fornecedor fornecedor = fornecedorRepository.findById(id).orElse(null);
        if (fornecedor == null) {
            return Resultado.erro("Fornecedor nao encontrado!");
        } else {
            return Resultado.sucesso(fornecedorMapper.toDto(fornecedor));
        }
    }

    @Transactional
    public Resultado atualizarFornecedor(@Valid Long id, FornecedorDTO fornecedorDTO) {

        if (fornecedorDTO.getNome() == null || fornecedorDTO.getNome().trim().isEmpty()) {
            return Resultado.erro("O nome do fornecedor nao pode ser vazio!");
        }

        if (fornecedorDTO.getCnpj() == null || fornecedorDTO.getCnpj().trim().isEmpty()) {
            return Resultado.erro("O CNPJ do fornecedor nao pode ser vazio!");
        }

        if (fornecedorDTO.getTelefone() == null || fornecedorDTO.getTelefone().trim().isEmpty()) {
            return Resultado.erro("O telefone do fornecedor nao pode ser vazio!");
        }

        if (fornecedorDTO.getEmail() == null || fornecedorDTO.getEmail().trim().isEmpty()) {
            return Resultado.erro("O email do fornecedor nao pode ser vazio!");
        }

        String cnpjLimpo = fornecedorDTO.getCnpj().replaceAll("[^0-9]", "");
        if (ValidarCnpj(cnpjLimpo)) {
            return Resultado.erro("O CNPJ do fornecedor não eh valido!");
        }

        if (!ValidarEmailFornecedor(fornecedorDTO.getEmail())) {
            return Resultado.erro("O email do fornecedor não eh valido!");
        }

        if (fornecedorRepository.existsByCnpj(cnpjLimpo)) {
            return Resultado.erro("Já existe um fornecedor com este CNPJ!");
        }

        Fornecedor fornecedor = fornecedorRepository.getById(id);
        if (fornecedor == null) {
            return Resultado.erro("Produto nao encontrado!");
        }

        fornecedor.setNome(fornecedorDTO.getNome());
        fornecedor.setCnpj(fornecedorDTO.getCnpj());
        fornecedor.setTelefone(fornecedorDTO.getTelefone());
        fornecedor.setEmail(fornecedorDTO.getEmail());
        fornecedorRepository.save(fornecedor);
        return Resultado.sucesso(fornecedorMapper.toDto(fornecedor));
    }

    @Transactional
    public Resultado removerFornecedor(@Valid Long id) {

        if (!fornecedorRepository.existsById(id)) {
            return Resultado.sucesso("Fornecedor nao encontrado!");
        } else {
            fornecedorRepository.deleteById(id);
            return Resultado.sucesso("Fornecedor removido com sucesso!");
        }
    }

}
