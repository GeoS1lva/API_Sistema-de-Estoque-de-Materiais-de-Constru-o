package com.example.demo.service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.DTO.CategoriaDTO;
import com.example.demo.domain.Categoria;
import com.example.demo.mapper.CategoriaMapper;
import com.example.demo.repository.ICategoriaRepository;

import org.springframework.util.StringUtils;
import jakarta.transaction.Transactional;



@Service
public class CategoriaService {

@Autowired
private ICategoriaRepository categoriaRepository;

@Autowired
private CategoriaMapper categoriaMapper;

@Transactional
public Resultado registrarCategoria(CategoriaDTO categoriaDTO) {
    if(!StringUtils.hasText(categoriaDTO.getNome())) {
        return Resultado.erro("O nome da categoria não pode ser vazio!");
    }

    if (categoriaRepository.existsByNome(categoriaDTO.getNome())) {
        return Resultado.erro("Categoria já existe!");
    }

    if (categoriaDTO.getDescricao().length() > 255) {
        return Resultado.erro("A descrição da categoria não pode ter mais de 255 caracteres!");
    }

    Categoria categoria = categoriaMapper.tEntity(categoriaDTO);
    Categoria savedCategoria = categoriaRepository.save(categoria);

    return Resultado.sucesso(categoriaMapper.tDto(savedCategoria));
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

public Resultado detalharCategoria(Long id) {
    Categoria categoria = categoriaRepository.findById(id).orElse(null);
    return categoria != null ? Resultado.sucesso(categoriaMapper.tDto(categoria)) : Resultado.erro("Categoria não encontrada!");
}
            
    }
    

                






    

