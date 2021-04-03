package io.github.erick.backendCllientes.service;

import io.github.erick.backendCllientes.exception.GenericException;
import io.github.erick.backendCllientes.exception.ProdutoException;
import io.github.erick.backendCllientes.model.entity.*;
import io.github.erick.backendCllientes.model.repository.*;
import io.github.erick.backendCllientes.rest.dto.*;
import io.github.erick.backendCllientes.util.ReplaceString;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final CategoriaProdutoRepository categoriaProdutoRepository;
    private final TipoProdutoRepository tipoProdutoRepository;
    private final CategoriaProdutoService categoriaProdutoService;

    public Produto salvarProduto(ProdutoDTO produtoDTO){

        CategoriaProduto categoriaProduto = categoriaProdutoRepository
                .findById(produtoDTO.getCategoriaProduto().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Categoria inexistente"));

        TipoProduto tipoProduto = tipoProdutoRepository
                .findById(produtoDTO.getTipoProduto().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Tipo inexistente"));


        Produto produto = new Produto();
        produto.setNomeProduto(produtoDTO.getNomeProduto());
        produto.setCategoriaProduto(produtoDTO.getCategoriaProduto());
        produto.setTipoProduto(produtoDTO.getTipoProduto());
        produto.setValorCompra(produtoDTO.getValorCompra());
        produto.setValorVenda(produtoDTO.getValorVenda());

        return produtoRepository.save(produto);
    }

    public void atualizarProduto(Integer id, ProdutoDTO produtoDTO){

        produtoRepository.findById(id)
                .map(produtoAtualizado -> {
                    produtoAtualizado.setNomeProduto(produtoDTO.getNomeProduto());
                    produtoAtualizado.setTipoProduto(produtoDTO.getTipoProduto());
                    produtoAtualizado.setCategoriaProduto(produtoDTO.getCategoriaProduto());
                    produtoAtualizado.setValorCompra(produtoDTO.getValorCompra());
                    produtoAtualizado.setValorVenda(produtoDTO.getValorVenda());
                    produtoRepository.save(produtoAtualizado);
                    return Void.TYPE;
                })
                .orElseThrow(() -> new ProdutoException("Produto inexistente",""));
    }

    public void deleteProduto(Integer id){
        produtoRepository.findById(id)
                .map(produto -> {
                    produtoRepository.delete(produto);
                    return Void.TYPE;
                })
                .orElseThrow(() -> new ProdutoException("Produto inexistente",""));
    }

    public Page<Produto> listaProduto(Integer pagina, Integer tamanhoPagina){
        PageRequest pageRequest = PageRequest.of(pagina, tamanhoPagina, Sort.by("nomeProduto").ascending());
        return produtoRepository.findAll(pageRequest);
    }
    public Page<Produto> buscarProduto(Integer pagina, Integer tamanhoPagina, String filtro){
        PageRequest pageRequest = PageRequest.of(pagina, tamanhoPagina, Sort.by("nomeProduto").ascending());
        return produtoRepository.buscarProduto(filtro, pageRequest);
    }

    public List<InforProduto> carregarProdutos(){
        List<Produto> produtos = produtoRepository.findAll();
      return produtos.stream().map(produto ->
            InforProduto.builder()
                    .id(produto.getId())
                    .nomeProduto(produto.getNomeProduto())
                    .valorCompra(produto.getValorCompra())
                    .valorVenda(produto.getValorVenda())
                    .categoriaProduto(categoriaProdutoService.converterInfoCatProdDTO(produto.getCategoriaProduto()))
                    .build()
        ).collect(Collectors.toList());
    }
}
