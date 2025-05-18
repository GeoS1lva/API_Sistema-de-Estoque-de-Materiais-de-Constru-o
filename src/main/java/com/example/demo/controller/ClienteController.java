package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.DTO.ClienteDTO;
import com.example.demo.service.ClienteService;
import com.example.demo.service.Resultado;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("api/clientes")
@Tag(name = "Clientes", description = "Gerenciamento de clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping
    public ResponseEntity<?> cadastrarCliente(@RequestBody @Valid ClienteDTO clienteDTO){
        Resultado resultado = clienteService.cadastrarCliente(clienteDTO);

        if(resultado.getErro()){
            return ResponseEntity.badRequest().body(resultado.getMensagemErro());
        }

        return ResponseEntity.ok(resultado.getValor());
    }

    @GetMapping("/listarClientes")
    public ResponseEntity<?> listasClientes(){
        Resultado resultado = clienteService.listarClientes();

        if(resultado.getErro()){
            return ResponseEntity.badRequest().body(resultado.getMensagemErro());
        }

        return ResponseEntity.ok(resultado.getValor());
    }

    @DeleteMapping("/{cpf}")
        public ResponseEntity<?> deletarPorCpf(@PathVariable String cpf){
        Resultado resultado = clienteService.deletarPorCpf(cpf);

        if(resultado.getErro()){
            return ResponseEntity.badRequest().body(resultado.getMensagemErro());
        }

        return ResponseEntity.ok(resultado.getValor());
    }
    
}
