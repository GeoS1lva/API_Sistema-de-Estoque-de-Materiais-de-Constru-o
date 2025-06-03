/*
 * Geovana Paula da Silva RA 170610-2024
*/

package com.example.demo.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.DTO.CompraDTO;
import com.example.demo.DTO.CompraResponseDTO;
import com.example.demo.domain.Compra;
import com.example.demo.domain.Fornecedor;
import com.example.demo.domain.Produto;
import com.example.demo.mapper.CompraMapper;
import com.example.demo.repository.ICompraRepository;
import com.example.demo.repository.IFornecedorRepository;
import com.example.demo.repository.IProdutoRepository;

@Service
public class CompraService {

    @Autowired
    private ICompraRepository compraRepository;

    @Autowired
    private IProdutoRepository produtoRepository;

    @Autowired
    private IFornecedorRepository fornecedorRepository;

    @Autowired
    private CompraMapper compraMapper;

    public Resultado registrarCompra(CompraDTO compraDTO){
        if(!produtoRepository.existsById(compraDTO.getProdutoId())){
            return Resultado.erro("Produto não encontrado!");
        }

        Produto produto = produtoRepository.getById(compraDTO.getProdutoId());

        if(!fornecedorRepository.existsById(compraDTO.getFornecedorId())){
            return Resultado.erro("Fornecedor não encontrado!");
        }

        Fornecedor fornecedor = fornecedorRepository.getById(compraDTO.getFornecedorId());

        atualizarQuantidade(produto, compraDTO.getQuantidade());

        Compra novaCompra = new Compra(produto, fornecedor, compraDTO.getQuantidade());

        produtoRepository.save(produto);

        compraRepository.save(novaCompra);

        return Resultado.sucesso(compraMapper.toDTO(novaCompra));
    }

    public Resultado listarCompras(){
        List<Compra> compras = compraRepository.findAll();

        if(compras.isEmpty()){
            return Resultado.erro("Não a compras registradas");
        }

        return Resultado.sucesso(compraMapper.toDTOList(compras));
    }

    public Resultado detalharCompra(Long compraId){
        if(!compraRepository.existsById(compraId)){
            return Resultado.erro("Registro de Compra não encontrada!");
        }

        Compra compra = compraRepository.getById(compraId);

        return Resultado.sucesso(compraMapper.toDTO(compra));
    }

    public Resultado listarComprasFornecedor(Long fornecedorId){
        if(!fornecedorRepository.existsById(fornecedorId)){
            return Resultado.erro("Fornecedor não encontrado!");
        }

        List<Compra> compras = compraRepository.findAll();

        List<CompraResponseDTO> comprasFornecedor = compraMapper.toDTOList(
            compras.stream()
            .filter(c -> c.getFornecedor().getId() == fornecedorId)
            .collect(Collectors.toList())
        );

        if(comprasFornecedor.isEmpty()){
            return Resultado.erro("Esse fornecedor não possui compras registradas");
        }

        return Resultado.sucesso(comprasFornecedor);
    }

    public Resultado listarComprasProduto(Long produtoId){
        if(!produtoRepository.existsById(produtoId)){
            return Resultado.erro("Produto não encontrado!");
        }

        List<Compra> compras = compraRepository.findAll();

        List<CompraResponseDTO> comprasProduto = compraMapper.toDTOList(
            compras.stream()
            .filter(c -> c.getProduto().getId() == produtoId)
            .collect(Collectors.toList())
        );

        if(comprasProduto.isEmpty()){
            return Resultado.erro("Não foi registrado uma compra desse produto!");
        }

        return Resultado.sucesso(comprasProduto);
    }

    public Resultado atualizarCompra(Long compraId, Integer quantidade){
        if(!compraRepository.existsById(compraId)){
            return Resultado.erro("Registro de Compra não encontrada!");
        }

        Compra compra = compraRepository.getById(compraId);

        Produto produto = produtoRepository.getById(compra.getProduto().getId());

        atualizarQuantidadeCompra(produto, compra.getQuantidade());
        atualizarQuantidadeCompraProduto(produto, compra, quantidade);
        compra.setQuantidade(quantidade);

        produtoRepository.save(produto);
        compraRepository.save(compra);

        return Resultado.sucesso(compraMapper.toDTO(compra));
    }

    public Resultado deletarCompra(Long compraId){
        if(!compraRepository.existsById(compraId)){
            return Resultado.erro("Registro de Compra não encontrada!");
        }

        Compra deletarCompra = compraRepository.getById(compraId);

        Produto produto = produtoRepository.getById(deletarCompra.getProduto().getId());

        atualizarQuantidadeCompra(produto, deletarCompra.getQuantidade());

        produtoRepository.save(produto);
        
        compraRepository.deleteById(compraId);
        return Resultado.sucesso("Registro de Compra excluída com Sucesso!");
    }

    private void atualizarQuantidadeCompraProduto(Produto produto, Compra compra, Integer quantidade){
        Integer valorAtualizado = produto.getQuantidade() + quantidade;
        BigDecimal valorTotalAtualizado = produto.getPrecoUnitario().multiply(BigDecimal.valueOf(quantidade));

        produto.setQuantidade(valorAtualizado);
        compra.setValorTotal(valorTotalAtualizado);
    }

    private void atualizarQuantidade(Produto produto, Integer quantidade){
        Integer valorAtualizado = produto.getQuantidade() + quantidade;

        produto.setQuantidade(valorAtualizado);
    }

    private void atualizarQuantidadeCompra(Produto produto, Integer quantidade){
        Integer valorAtualizado = produto.getQuantidade() - quantidade;

        produto.setQuantidade(valorAtualizado);
    }
    
}
