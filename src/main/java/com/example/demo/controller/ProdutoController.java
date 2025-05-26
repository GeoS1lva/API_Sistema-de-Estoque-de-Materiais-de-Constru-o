/*---------------------
Autor: Eduardo Bernardes Zanin
---------------------*/

package com.example.demo.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.DTO.ProdutoDTO;
import com.example.demo.service.ProdutoService;
import com.example.demo.service.Resultado;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("api/produtos")
@Tag(name = "Produtos", description = "Gerenciamento de Produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @PostMapping("/cadastrarProduto")
    public ResponseEntity<?> cadastrarProduto(@Valid @RequestBody ProdutoDTO produtoDTO) {

        if (produtoDTO.getCategoriaId() == null) {
            return ResponseEntity.badRequest().body("O ID da categoria não pode ser nulo.");
        }

        Resultado resultado = produtoService.cadastrarProduto(produtoDTO);
        if (resultado.getErro()) {
            return ResponseEntity.badRequest().body("Erro ao cadastrar produto: " + resultado.getMensagemErro());
        }

        return ResponseEntity.ok(resultado.getValor());
    }

    @GetMapping("/listarProdutos")
    public ResponseEntity<?> listarProdutos() {
        List<ProdutoDTO> produtos = produtoService.listarProdutos();

        if (produtos == null || produtos.isEmpty()) {
            return ResponseEntity.badRequest().body("Nenhum produto encontrado.");
        }

        return ResponseEntity.ok(produtos);
    }

    @GetMapping("/detalharProdutos/{id}")
    public ResponseEntity<?> detalharProduto(@Valid @PathVariable Long id) {
        Resultado resultado = produtoService.detalharProduto(id);
        if (resultado.getErro()) {
            return ResponseEntity.badRequest().body(resultado.getMensagemErro());
        }
        return ResponseEntity.ok(resultado.getValor());
    }

    @PutMapping("/atualizarProduto/{id}")
    public ResponseEntity<?> atualizarProduto(@Valid @PathVariable Long id, @RequestBody ProdutoDTO produtoDTO) {
        Resultado resultado = produtoService.atualizarProduto(id, produtoDTO);
        if (resultado.getErro()) {
            return ResponseEntity.badRequest().body("Nenhum produto encontrado com o ID: " + id);
        }
        return ResponseEntity.ok(produtoDTO);

    }

    @DeleteMapping("/removerProduto/{id}")
    public ResponseEntity<?> removerProduto(@Valid Long id) {
        Resultado resultado = produtoService.removerProduto(id);

        if (resultado.getErro()) {
            return ResponseEntity.badRequest().body(resultado.getMensagemErro());
        }
        return ResponseEntity.ok(resultado.getValor());
    }

}
