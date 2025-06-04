package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.DTO.FornecedorDTO;
import com.example.demo.service.FornecedorService;
import com.example.demo.service.Resultado;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/fornecedores")
@Tag(name = "Fornecedores", description = "Gerenciamento de Fornecedores")
public class FornecedorController {

    @Autowired
    private FornecedorService fornecedorService;

    @PostMapping("/cadastrarFornecedor")
    public ResponseEntity<?> cadastrarFornecedor(@Valid @RequestBody FornecedorDTO fornecedorDTO) {
        Resultado resultado = fornecedorService.cadastrarFornecedor(fornecedorDTO);

        if (resultado.getErro()) {
            return ResponseEntity.badRequest().body(resultado.getMensagemErro());
        } else {
            return ResponseEntity.ok(resultado.getValor());
        }
    }

    @GetMapping("/listarFornecedores")
    public ResponseEntity<?> listarFornecedores() {
        List<FornecedorDTO> fornecedores = fornecedorService.listarFornecedores();

        if (fornecedores.isEmpty() || fornecedores == null) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(fornecedores);

        }
    }

    @GetMapping("/detalharFornecedor/{id}")
    public ResponseEntity<?> detalharFornecedor(@Valid @PathVariable Long id) {
        Resultado resultado = fornecedorService.detalharFornecedor(id);

        if (resultado.getErro()) {
            return ResponseEntity.badRequest().body(resultado.getMensagemErro());
        } else {
            return ResponseEntity.ok(resultado.getValor());
        }
    }

    @PutMapping("/atualizarFornecedor/{id}")
    public ResponseEntity<?> atualizarFornecedor(@PathVariable Long id,
            @Valid @RequestBody FornecedorDTO fornecedorDTO) {
        Resultado resultado = fornecedorService.atualizarFornecedor(id, fornecedorDTO);

        if (resultado.getErro()) {
            return ResponseEntity.badRequest().body(resultado.getMensagemErro());
        } else {
            return ResponseEntity.ok(resultado.getValor());
        }
    }

    @DeleteMapping("/removerFornecedor/{id}")
    public ResponseEntity<?> removerFornecedor(@PathVariable Long id) {
        Resultado resultado = fornecedorService.removerFornecedor(id);

        if (resultado.getErro()) {
            return ResponseEntity.badRequest().body(resultado.getMensagemErro());
        } else {
            return ResponseEntity.ok(resultado.getValor());
        }
    }

}
