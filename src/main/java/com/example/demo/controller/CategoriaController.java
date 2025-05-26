/*---------------------
Autor: Eduardo Bernardes Zanin
---------------------*/

package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.DTO.CategoriaDTO;
import com.example.demo.DTO.ProdutoDTO;
import com.example.demo.service.CategoriaService;
import com.example.demo.service.Resultado;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/categorias")
@Tag(name = "Categorias", description = "Gerenciamento de Categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @PostMapping("/registrarCategoria")
    public ResponseEntity<?> registrarCategoria(@Valid @RequestBody CategoriaDTO categoriaDTO) {
        Resultado resultado = categoriaService.registrarCategoria(categoriaDTO);

        if (resultado.getErro()) {
            return ResponseEntity.badRequest().body(resultado.getMensagemErro());
        } else {
            return ResponseEntity.ok(resultado.getValor());
        }
    }

    @GetMapping("/listarCategoria")
    public ResponseEntity<?> listarCategoria() {
        List<CategoriaDTO> categorias = categoriaService.listarCategoria();

        if (categorias == null || categorias.isEmpty()) {
            return ResponseEntity.badRequest().body("Nenhuma categoria encontrada.");
        } else {
            return ResponseEntity.ok(categorias);
        }
    }

    @GetMapping("/detalharCategoria/{id}")
    public ResponseEntity<?> detalharCategoria(@PathVariable Long id) {
        Resultado resultado = categoriaService.detalharCategoria(id);

        if (resultado.getErro()) {
            return ResponseEntity.badRequest().body(resultado.getMensagemErro());
        } else {
            return ResponseEntity.ok(resultado.getValor());
        }
    }

    @GetMapping("/listarProdutosPorCategoria/{nomeCategoria}")
    public ResponseEntity<?> listarProdutosPorCategoria(@RequestParam String nomeCategoria) {
        List<ProdutoDTO> produtoDTOs = categoriaService.listarProdutosPorCategoria(nomeCategoria);

        if (produtoDTOs == null || produtoDTOs.isEmpty()) {
            return ResponseEntity.badRequest().body("Nenhuma categoria encontrada.");
        } else {
            return ResponseEntity.ok(produtoDTOs);
        }
    }

    @PutMapping("/atualizarCategoria/{id}")
    public ResponseEntity<?> atualizarCategoria(@PathVariable Long id, @RequestBody @Valid CategoriaDTO categoriaDTO) {
        Resultado resultado = categoriaService.atualizarCategoria(id, categoriaDTO);

        if (resultado.getErro()) {
            return ResponseEntity.badRequest().body("Nenhuma categoria encontrada.");
        } else {
            return ResponseEntity.ok(categoriaDTO);
        }
    }

    @DeleteMapping("/removerCategoria{id}")
    public ResponseEntity<?> RemoverCategoria(Long id) {
        Resultado resultado = categoriaService.RemoverCategoria(id);

        if (resultado.getErro()) {
            return ResponseEntity.badRequest().body(resultado.getMensagemErro());
        } else {
            return ResponseEntity.ok(resultado.getValor());
        }
    }

}
