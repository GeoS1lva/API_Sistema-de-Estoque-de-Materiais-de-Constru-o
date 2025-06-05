/*
 * Geovana Paula da Silva RA 170610-2024
*/

package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.DTO.CompraDTO;
import com.example.demo.service.CompraService;
import com.example.demo.service.Resultado;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping("api/compras")
@Tag(name = "Compras", description = "Gerenciamento de compras")
public class CompraController {

    @Autowired
    private CompraService compraService;

    @PostMapping
    @Operation(description = "Registra uma nova compra")
    public ResponseEntity<?> registrarCompra(@RequestBody @Valid CompraDTO compraDTO){
        Resultado resultado = compraService.registrarCompra(compraDTO);

        if(resultado.getErro()){
            return ResponseEntity.badRequest().body(resultado.getMensagemErro());
        }

        return ResponseEntity.ok(resultado.getValor());
    }

    @GetMapping
    @Operation(description = "Lista todas as compras")
    public ResponseEntity<?> listarCompras(){
        Resultado resultado = compraService.listarCompras();

        if(resultado.getErro()){
            return ResponseEntity.badRequest().body(resultado.getMensagemErro());
        }

        return ResponseEntity.ok(resultado.getValor());
    }

    @GetMapping("/{id}")
    @Operation(description = "Obtém detalhes de uma compra por ID")
    public ResponseEntity<?> detalharCompra(@PathVariable Long id){
        Resultado resultado = compraService.detalharCompra(id);

        if(resultado.getErro()){
            return ResponseEntity.badRequest().body(resultado.getMensagemErro());
        }

        return ResponseEntity.ok(resultado.getValor());
    }

    @GetMapping("/fornecedor/{fornecedorId}")
    @Operation(description = "Lista compras de um fornecedor")
    public ResponseEntity<?> listarCompraFornecedor(@PathVariable Long fornecedorId){
        Resultado resultado = compraService.listarComprasFornecedor(fornecedorId);

        if(resultado.getErro()){
            return ResponseEntity.badRequest().body(resultado.getMensagemErro());
        }

        return ResponseEntity.ok(resultado.getValor());
    }

    @GetMapping("/produto/{produtoId}")
    @Operation(description = "Lista compras de um produto")
    public ResponseEntity<?> listarCompraProduto(@PathVariable Long produtoId){
        Resultado resultado = compraService.listarComprasProduto(produtoId);

        if(resultado.getErro()){
            return ResponseEntity.badRequest().body(resultado.getMensagemErro());
        }

        return ResponseEntity.ok(resultado.getValor());
    }

    @PutMapping("/{id}")
    @Operation(description = "Atualiza a quantidade de uma compra")
    public ResponseEntity<?> atualizarCompra(@PathVariable Long id, Integer quantidade){
        Resultado resultado = compraService.atualizarCompra(id, quantidade);

        if(resultado.getErro()){
            return ResponseEntity.badRequest().body(resultado.getMensagemErro());
        }

        return ResponseEntity.ok(resultado.getValor());
    }

    @DeleteMapping("/{id}")
    @Operation(description = "Remove uma compra")
    public ResponseEntity<?> deletarCompra(@PathVariable Long id){
        Resultado resultado = compraService.deletarCompra(id);

        if(resultado.getErro()){
            return ResponseEntity.badRequest().body(resultado.getMensagemErro());
        }

        return ResponseEntity.ok(resultado.getValor());
    }
    
}
