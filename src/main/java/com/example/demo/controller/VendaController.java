/*
 * Geovana Paula da Silva RA 170610-2024
*/

package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.DTO.VendaDTO;
import com.example.demo.service.Resultado;
import com.example.demo.service.VendaService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("api/vendas")
@Tag(name = "Vendas", description = "Cadastro e gerenciamento das vendas")
public class VendaController {

    @Autowired
    private VendaService vendaService;

    @PostMapping
    public ResponseEntity<?> registrarVenda(@RequestBody @Valid VendaDTO vendaDTO){
        Resultado resultado = vendaService.realizarVenda(vendaDTO);

        if(resultado.getErro()){
            return ResponseEntity.badRequest().body(resultado.getMensagemErro());
        }

        return ResponseEntity.ok(resultado.getValor());
    }

    @GetMapping
    public ResponseEntity<?> listarVendas(){
        Resultado resultado = vendaService.listasVendas();

        if(resultado.getErro()){
            return ResponseEntity.badRequest().body(resultado.getMensagemErro());
        }

        return ResponseEntity.ok(resultado.getValor());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> detalharVenda(@PathVariable Long id){
        Resultado resultado = vendaService.detalharVenda(id);

        if(resultado.getErro()){
            return ResponseEntity.badRequest().body(resultado.getMensagemErro());
        }

        return ResponseEntity.ok(resultado.getValor());
    }

    @DeleteMapping("/{Id}")
    public ResponseEntity<?> removerVenda(@PathVariable Long id){
        Resultado resultado = vendaService.deletarVenda(id);

        if(resultado.getErro()){
            return ResponseEntity.badRequest().body(resultado.getMensagemErro());
        }

        return ResponseEntity.ok(resultado.getValor());
    }
    
}
