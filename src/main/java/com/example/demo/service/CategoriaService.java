package com.example.demo.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.DTO.CategoriaDTO;
import com.example.demo.DTO.ProdutoDTO;
import com.example.demo.domain.Categoria;
import com.example.demo.mapper.CategoriaMapper;
import com.example.demo.mapper.ProdutoMapper;
import com.example.demo.repository.ICategoriaRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Service
public class CategoriaService {

    @Autowired
    private ICategoriaRepository categoriaRepository;

    @Autowired
    private CategoriaMapper categoriaMapper;

    @Autowired
    private ProdutoMapper produtoMapper;

    @Transactional
    public Resultado registrarCategoria(CategoriaDTO categoriaDTO) {
        if (categoriaDTO.getNome() == null || categoriaDTO.getNome().trim().isEmpty()) {
            return Resultado.erro("O nome da categoria não pode ser vazio!");
        }

        if (categoriaRepository.existsByNome(categoriaDTO.getNome())) {
            return Resultado.erro("Categoria já existe!");
        }

        if (categoriaDTO.getDescricao() == null || categoriaDTO.getDescricao().isEmpty()) {
            return Resultado.erro("A descrição da categoria não pode ser vazia!");
        }

        if (categoriaDTO.getDescricao().length() > 125) {
            return Resultado.erro("A descrição da categoria não pode ter mais de 125 caracteres!");
        }

        Categoria categoria = categoriaMapper.tEntity(categoriaDTO);
        categoriaRepository.save(categoria);

        return Resultado.sucesso("Categoria cadastrada com sucesso com ID: " + categoria.getId());

    }

    public List<CategoriaDTO> listarCategoria() {
        List<Categoria> categorias = categoriaRepository.findAll();
        if (categorias.isEmpty()) {
            return Collections.emptyList();
        } else {
            return categorias.stream()
                    .map(categoriaMapper::tDto)
                    .collect(Collectors.toList());
        }
    }

    public Resultado detalharCategoria(@Valid Long id) {
        Categoria categoria = categoriaRepository.findById(id).orElse(null);
        if (categoria == null) {
            return Resultado.erro("Categoria nao encontrada!");
        } else {
            return Resultado.sucesso(categoriaMapper.tDto(categoria));
        }
    }

    public List<ProdutoDTO> listarProdutosPorCategoria(String nomeCategoria) {
        Categoria categoria = categoriaRepository.findByNome(nomeCategoria).orElse(null);
        if (categoria == null) {
            Resultado.erro("Categoria não encontrada!");
        }
        return categoria.getProdutos().stream()
                .map(produtoMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public Resultado atualizarCategoria(Long id, CategoriaDTO categoriaDTO) {
        if (categoriaDTO.getNome() == null || categoriaDTO.getNome().trim().isEmpty()) {
            return Resultado.erro("O nome da categoria não pode ser vazio!");
        }

        if (categoriaRepository.existsByNome(categoriaDTO.getNome())) {
            return Resultado.erro("Já existe uma categoria com esse nome");
        }

        if (categoriaDTO.getDescricao() == null || categoriaDTO.getDescricao().isEmpty()) {
            return Resultado.erro("A descrição da categoria não pode ser vazia!");
        }

        if (categoriaDTO.getDescricao().length() > 125) {
            return Resultado.erro("A descrição da categoria não pode ter mais de 125 caracteres!");
        }

        Optional<Categoria> categoriaOptional = categoriaRepository.findById(id);
        if (categoriaOptional.isPresent()) {
            Categoria categoria = categoriaOptional.get();
            categoria.setNome(categoriaDTO.getNome());
            categoria.setDescricao(categoriaDTO.getDescricao());
            categoriaRepository.save(categoria);
            return Resultado.sucesso(categoriaMapper.tDto(categoria));
        }
        return Resultado.erro("Categoria não encontrada!");
    }

    @Transactional
    public Resultado RemoverCategoria(Long id) {
        if (!categoriaRepository.existsById(id)) {
            return Resultado.erro("Categoria não encontrada!");
        }
        categoriaRepository.deleteById(id);
        return Resultado.sucesso("Categoria excluída com sucesso!");
    }
}
