package io.github.erick.backendCllientes.service;

import io.github.erick.backendCllientes.exception.ClienteException;
import io.github.erick.backendCllientes.exception.GenericException;
import io.github.erick.backendCllientes.model.entity.*;
import io.github.erick.backendCllientes.model.repository.*;
import io.github.erick.backendCllientes.rest.dto.*;
import lombok.AllArgsConstructor;
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
public class LancamentoProdutoService {

    private final LancamentoProdutoRepository repository;
    private final LancamentoRepository lancamentoRepository;
    private final ProdutoRepository produtoRepository;
    private final LancamentoAdicionalRepository lancamentoAdicionalRepository;
    private final AdicionalRepository adicionalRepository;
    private final LancamentoService lancamentoService;

    @Transactional
    public List<InfoLanProdutoDTO> salvar(LancProdArrayDto dto){

        List<LancamentoProduto> lancamentoProdutos = converterProdutos(dto.getLancamento(), dto.getProdutos());

        return converterInfoLanProdutoDTO(lancamentoProdutos);

    }



    public List<LancamentoProduto> converterProdutos(Integer lancamentoId, List<LancamentoProdutosDTO> dto){

        return dto.stream()
                .map(dto1 -> {
                    if (dto1.getProduto() == null){
                        return null;
                    }

                    Integer produtoId = dto1.getProduto();

                    Produto produto = produtoRepository.findById(produtoId)
                            .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Produto selecionado não existe"));

                    Lancamento lancamento = lancamentoRepository.findById(lancamentoId)
                            .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Lancamento não existe"));

                    LancamentoProduto lancamentoProduto = new LancamentoProduto();
                    lancamentoProduto.setId(dto1.getId());
                    lancamentoProduto.setLancamento(lancamento);
                    lancamentoProduto.setProduto(produto);
                    lancamentoProduto.setDesconto(dto1.getDesconto());
                    lancamentoProduto.setTotalProduto(dto1.getTotalProduto());
                    lancamentoProduto.setQuantidade(dto1.getQuantidade());
                    lancamentoProduto.setValorVenda(dto1.getValorVenda());
                    try {
                        repository.save(lancamentoProduto);
                    }catch (Exception e){
                        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
                    }

                    List<LancamentoAdicional> lancamentoAdicionals =  converterAdicional(lancamento,lancamentoProduto ,dto1.getAdicionais());
                    lancamentoProduto.setAdicionais(lancamentoAdicionals);
                    return lancamentoProduto;
                }).collect(Collectors.toList());
    }

    private List<LancamentoAdicional> converterAdicional(Lancamento lancamento ,LancamentoProduto lancamentoProduto,List<LancamentoAdicionalDTO> adicional){
        if (CollectionUtils.isEmpty(adicional)){
            Collections.emptyList();
        }

        return adicional.stream().map(
                adicional1 ->{
                    Integer lancamentoAdicionalId = adicional1.getId();
                    Adicional adicional2 = adicionalRepository.findById(lancamentoAdicionalId)
                            .orElseThrow(()-> new ResponseStatusException(HttpStatus.BAD_REQUEST,"Desculpe ocorreu um erro ao apagar o produto!"));

                    LancamentoAdicional lancamentoAdicional = new LancamentoAdicional();
                    lancamentoAdicional.setLancamentoProduto(lancamentoProduto);
                    lancamentoAdicional.setLancamento(lancamento);
                    lancamentoAdicional.setAdicional(adicional2);
                    lancamentoAdicional.setStatus(adicional1.getSelecionaAdicionais());
                    try {
                        lancamentoAdicionalRepository.save(lancamentoAdicional);
                    }catch (Exception e){
                        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
                    }

                    return lancamentoAdicional;
                }

        ).collect(Collectors.toList());
    }

    private List<InfoLanProdutoDTO> converterInfoLanProdutoDTO(List<LancamentoProduto> lancamentoProdutos ){
      return   lancamentoProdutos.stream()
                .map(lancamentoProduto ->
                        InfoLanProdutoDTO.builder()
                        .id(lancamentoProduto.getId())
                        .produto(lancamentoProduto.getProduto().getId())
                        .nomeProduto(lancamentoProduto.getProduto().getNomeProduto())
                        .valorVenda(lancamentoProduto.getValorVenda())
                        .desconto(lancamentoProduto.getDesconto())
                        .quantidade(lancamentoProduto.getQuantidade())
                        .totalProduto(lancamentoProduto.getTotalProduto())
                        .adicionais(converterInfoLancamentoAdicionalDTO(lancamentoProduto.getAdicionais()))
                        .build()
                        ).collect(Collectors.toList());

    }

    //converte list de lancamentoAdicional em infoLancamentoAdicionalDTO
    private List<InfoLancamentoAdicionalDTO> converterInfoLancamentoAdicionalDTO(List<LancamentoAdicional> lancamentoAdicional){
        if (CollectionUtils.isEmpty(lancamentoAdicional)){
            Collections.emptyList();
        }
        return lancamentoAdicional.stream()
                .map(lA ->
                        InfoLancamentoAdicionalDTO.builder()
                                .lancamentoAdicionalId(lA.getLancamento().getId())
                                .lancamentoProdutoId(lA.getLancamentoProduto().getId())
                                .adicionalId(lA.getAdicional().getId())
                                .nome(lA.getAdicional().getNome())
                                .status(lA.getStatus())
                                .build()
                ).collect(Collectors.toList());

    }
    public void deleteLancamento(Integer id) {
        repository.findById(id)
                .map(lancamentoProduto -> {
                    repository.delete(lancamentoProduto);
                    return Void.TYPE;
                })
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.BAD_REQUEST,"Desculpe ocorreu um erro ao apagar o produto!"));
    }
}
