package io.github.erick.backendCllientes.service;

import io.github.erick.backendCllientes.exception.ProdutoException;
import io.github.erick.backendCllientes.model.entity.CategoriaProduto;
import io.github.erick.backendCllientes.model.entity.Produto;
import io.github.erick.backendCllientes.model.entity.TipoProduto;
import io.github.erick.backendCllientes.model.repository.CategoriaProdutoRepository;
import io.github.erick.backendCllientes.model.repository.ProdutoRepository;
import io.github.erick.backendCllientes.model.repository.TipoProdutoRepository;
import io.github.erick.backendCllientes.rest.dto.ProdutoDTO;
import io.github.erick.backendCllientes.util.ReplaceString;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@AllArgsConstructor
public class ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final TipoProdutoRepository tipoProdutoRepository;
    private final CategoriaProdutoRepository categoriaProdutoRepository;
    private final ReplaceString replaceString;


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
        PageRequest pageRequest = PageRequest.of(pagina, tamanhoPagina);
        return produtoRepository.findAll(pageRequest);
    }
    public Page<Produto> buscarProduto(Integer pagina, Integer tamanhoPagina, String filtro){
        PageRequest pageRequest = PageRequest.of(pagina, tamanhoPagina);
        return produtoRepository.buscarProduto(filtro, pageRequest);
    }

    /* ---------CATEGORIA PRODUTO------------------- */


    public CategoriaProduto salvarCategoriaProduto(CategoriaProduto categoriaProduto){
        return categoriaProdutoRepository.save(categoriaProduto);
    }

    public void atualizarCategoriaProduto(Integer id, CategoriaProduto categoriaProdutoAtualizado){

        categoriaProdutoRepository.findById(id)
                .map(categoriaProduto1 -> {
                    categoriaProduto1.setNomeCategoriaProduto(categoriaProdutoAtualizado.getNomeCategoriaProduto());
                    categoriaProdutoRepository.save(categoriaProduto1);
                    return Void.TYPE;
                })
                .orElseThrow(() -> new ProdutoException("Categoria inexistente",""));
    }

    public void deleteCategoriaProduto(Integer id){
        categoriaProdutoRepository.findById(id)
                .map(categoriaProduto -> {
                    categoriaProdutoRepository.delete(categoriaProduto);
                    return  Void.TYPE;
                })
                .orElseThrow(() -> new ProdutoException("Categoria inexistente",""));
    }

    public List<CategoriaProduto> CarregarCategoriaProduto(){
        return  categoriaProdutoRepository.findAll();
    }

    public Page<CategoriaProduto> listaCategoriaProduto(Integer pagina, Integer tamanhoPagina){
        PageRequest pageRequest = PageRequest.of(pagina, tamanhoPagina);
        return categoriaProdutoRepository.findAll(pageRequest);
    }

    public Page<CategoriaProduto> buscarCategoriaProduto(String filtro, Integer pagina, Integer tamanhoPagina){
        PageRequest pageRequest = PageRequest.of(pagina, tamanhoPagina);
        return categoriaProdutoRepository.buscarCategoriaProduto(filtro, pageRequest);
    }


    /* -----------TIPO PRODUTO------------------- */

    public void atualizarTipoProduto(Integer id, TipoProduto tipoProduto){

        tipoProdutoRepository.findById(id)
                .map(tipoProduto1 -> {
                    tipoProduto1.setNomeTipoProduto(tipoProduto.getNomeTipoProduto());
                    tipoProdutoRepository.save(tipoProduto1);
                    return Void.TYPE;
                })
                .orElseThrow(() -> new ProdutoException("Tipo produto inexistente",""));
    }

    public void deleteTipoProduto(Integer id){
        tipoProdutoRepository.findById(id)
                .map(tipoProduto1 -> {
                    tipoProdutoRepository.delete(tipoProduto1);
                    return  Void.TYPE;
                })
                .orElseThrow(() -> new ProdutoException("Categoria inexistente",""));
    }

    public TipoProduto salvarTipoProduto(TipoProduto tipoProduto){
        return tipoProdutoRepository.save(tipoProduto);
    }

    public List<TipoProduto> carregarTipoProduto(){
        return  tipoProdutoRepository.findAll();
    }

    public Page<TipoProduto> listaTipoProduto(Integer pagina, Integer tamanhoPagina){
        PageRequest pageRequest = PageRequest.of(pagina, tamanhoPagina);
        return tipoProdutoRepository.findAll(pageRequest);
    }

    public Page<TipoProduto> buscarTipoProduto(String filtro, Integer pagina, Integer tamanhoPagina){
        PageRequest pageRequest = PageRequest.of(pagina, tamanhoPagina);
        return tipoProdutoRepository.buscartipoProduto(filtro, pageRequest);
    }
}
