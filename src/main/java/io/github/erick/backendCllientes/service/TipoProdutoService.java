package io.github.erick.backendCllientes.service;

import io.github.erick.backendCllientes.exception.ProdutoException;
import io.github.erick.backendCllientes.model.entity.TipoProduto;
import io.github.erick.backendCllientes.model.repository.TipoProdutoRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TipoProdutoService {

    private final TipoProdutoRepository repository;

    public void atualizarTipoProduto(Integer id, TipoProduto tipoProduto){

        repository.findById(id)
                .map(tipoProduto1 -> {
                    tipoProduto1.setNomeTipoProduto(tipoProduto.getNomeTipoProduto());
                    repository.save(tipoProduto1);
                    return Void.TYPE;
                })
                .orElseThrow(() -> new ProdutoException("Tipo produto inexistente",""));
    }

    public void deleteTipoProduto(Integer id){
        repository.findById(id)
                .map(tipoProduto1 -> {
                    repository.delete(tipoProduto1);
                    return  Void.TYPE;
                })
                .orElseThrow(() -> new ProdutoException("Categoria inexistente",""));
    }

    public TipoProduto salvarTipoProduto(TipoProduto tipoProduto){
        return repository.save(tipoProduto);
    }

    public List<TipoProduto> carregarTipoProduto(){
        return  repository.findAll();
    }

    public Page<TipoProduto> listaTipoProduto(Integer pagina, Integer tamanhoPagina){
        PageRequest pageRequest = PageRequest.of(pagina, tamanhoPagina, Sort.by("dataCadastro").descending());
        return repository.findAll(pageRequest);
    }

    public Page<TipoProduto> buscarTipoProduto(String filtro, Integer pagina, Integer tamanhoPagina){
        PageRequest pageRequest = PageRequest.of(pagina, tamanhoPagina, Sort.by("dataCadastro").descending());
        return repository.buscartipoProduto(filtro, pageRequest);
    }
}
