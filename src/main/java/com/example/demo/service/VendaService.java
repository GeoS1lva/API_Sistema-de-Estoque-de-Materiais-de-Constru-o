/*
 * Geovana Paula da Silva RA 170610-2024
*/

package com.example.demo.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.DTO.VendaDTO;
import com.example.demo.domain.Cliente;
import com.example.demo.domain.Produto;
import com.example.demo.domain.Venda;
import com.example.demo.mapper.VendaMapper;
import com.example.demo.repository.IClienteRepository;
import com.example.demo.repository.IProdutoRepository;
import com.example.demo.repository.IVendaRepository;

@Service
public class VendaService {
    
    @Autowired
    private IClienteRepository clienteRepository;

    @Autowired
    private IProdutoRepository produtoRepository;

    @Autowired
    private IVendaRepository vendaRepository;

    @Autowired
    private VendaMapper vendaMapper;

    public Resultado realizarVenda(VendaDTO vendaDTO){
        if(!clienteRepository.existsById(vendaDTO.getClienteId())){
            return Resultado.erro("Cliente não encontrado!");
        }

        Cliente cliente = clienteRepository.getById(vendaDTO.getClienteId());

        if(!produtoRepository.existsById(vendaDTO.getProdutoId())){
            return Resultado.erro("Produto não encontrado!");
        }

        Produto produto = produtoRepository.getById(vendaDTO.getProdutoId());

        if(produto.getQuantidade() <= vendaDTO.getQuantidade()){
            return Resultado.erro("Não temos a quantidade desejada do produto.");
        }

        atualizarQuantidade(produto, vendaDTO.getQuantidade());

        Venda venda = new Venda(cliente, produto, vendaDTO.getQuantidade()); 

        vendaRepository.save(venda);
        produtoRepository.save(produto);

        return Resultado.sucesso(vendaMapper.toDTO(venda));
    }

    public Resultado listasVendas(){
        List<Venda> vendas = vendaRepository.findAll();

        if(vendas.isEmpty()){
            return Resultado.erro("Não a Vendas registradas");
        }

        return Resultado.sucesso(vendaMapper.toDTOList(vendas));
    }

    public Resultado detalharVenda(Long vendaId){
        if(!vendaRepository.existsById(vendaId)){
            return Resultado.erro("Venda não encontrado!");
        }

        Venda venda = vendaRepository.getById(vendaId);

        return Resultado.sucesso(vendaMapper.toDTO(venda));
    }

    public Resultado deletarVenda(Long vendaId){
        if(!vendaRepository.existsById(vendaId)){
            return Resultado.erro("Venda não encontrada!");
        }
        
        vendaRepository.deleteById(vendaId);
        return Resultado.sucesso("Venda excluída com Sucesso!");
    }

    private void atualizarQuantidade(Produto produto, int quantidade){
        int valorAtualizado = produto.getQuantidade() - quantidade;

        produto.setQuantidade(valorAtualizado);
    }
}