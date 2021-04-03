package io.github.erick.backendCllientes.service;

import io.github.erick.backendCllientes.exception.ClienteException;
import io.github.erick.backendCllientes.exception.FornecedorException;
import io.github.erick.backendCllientes.exception.GenericException;
import io.github.erick.backendCllientes.exception.ProdutoException;
import io.github.erick.backendCllientes.model.entity.*;
import io.github.erick.backendCllientes.model.repository.*;
import io.github.erick.backendCllientes.rest.dto.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class LancamentoService {

    private final LancamentoRepository repository;
    private final ClienteRepository clienteRepository;
    private final ProdutoRepository produtoRepository;


    //Responsável por salvar o Lancamento
    @Transactional
    public Lancamento salvar(LancamentoDTO lancamentoDTO) {
        Integer clienteId = lancamentoDTO.getCliente();
        Cliente cliente = clienteRepository
                .findById(clienteId)
                .orElseThrow(() -> new ClienteException("Cliente não encontrado", ""));

        Lancamento lancamento = new Lancamento();
        lancamento.setCliente(cliente);
        lancamento.setDataLancamento(lancamentoDTO.getDataLancamento());
        lancamento.setCep(lancamentoDTO.getCep());
        lancamento.setBairro(lancamentoDTO.getBairro());
        lancamento.setLagradouro(lancamentoDTO.getLagradouro());
        lancamento.setComplemento(lancamentoDTO.getComplemento());
        lancamento.setNumero(lancamentoDTO.getNumero());
        lancamento.setCelular(lancamentoDTO.getCelular());
        lancamento.setTelefone(lancamentoDTO.getTelefone());
        lancamento.setStatus(lancamentoDTO.getStatus());
        lancamento.setTotal(lancamentoDTO.getTotal());

        try {
            repository.save(lancamento);
        } catch (Exception e) {
            throw new GenericException("Dados do pedido inválidos", e.toString());
        }
        //   List<LancamentoProduto> lancamentoProduto = converterProdutos(lancamento, lancamentoDTO.getProdutos());


        //
        // try {
        //     lancamentoProdutoRepository.saveAll(lancamentoProduto);
        // } catch (Exception e) {
        //     throw new GenericException("Dados do pedido inválidos", e.toString());
        // }
        //
        // prepAdiconal(lancamento, lancamentoProduto);
        //
        //
        // lancamento.setLancamentoProdutos(lancamentoProduto);
        return lancamento;
        //InformacoesLancamentoDTO informacoesLancamentoDTO = new InformacoesLancamentoDTO();
        //return informacoesLancamentoDTO = converter(lancamento);
    }  ;

    //Transformar os dados salvos para exibir ao usuario
    private InformacoesLancamentoDTO converter(Lancamento lancamento){
        return InformacoesLancamentoDTO.builder()
                .id(lancamento.getId())
                .nomeCliente(lancamento.getCliente().getNome())
                .dataLancamento(lancamento.getDataLancamento())
                .dataCadastro(lancamento.getDataCadastro())
                .total(lancamento.getTotal())
                .build();
    }

    // transforma o LancamentoProdutos em InfoLanProdutosDTO para exibir em InformacoesLancamentoDTO
    public List<InfoLanProdutosDTO> converter(List<LancamentoProduto> produtos){
        if (CollectionUtils.isEmpty(produtos)){
            Collections.emptyList();
        }

        return produtos.stream().map(
                produto ->
                        InfoLanProdutosDTO.builder()
                                .nomeProduto(produto.getProduto().getNomeProduto())
                                .quantidade(produto.getQuantidade())
                                .desconto(produto.getDesconto())
                                .valorVenda(produto.getValorVenda())
                                .build()
        ).collect(Collectors.toList());

    }

    public void deleteLancamento(Integer id) {
        repository.findById(id)
                .map(lancamento -> {
                    repository.deleteById(id);
                    return Void.TYPE;
                })
                .orElseThrow(()-> new FornecedorException("Desculpe ocorreu um erro ao apagar o lancamento!",""));
    }

    public void updateLancamento(Integer id, LancamentoDTO lancamentoAtualizado) {
        Integer clienteId = lancamentoAtualizado.getCliente();
        Cliente cliente = clienteRepository
                .findById(clienteId)
                .orElseThrow(() -> new ClienteException("Cliente não encontrado", ""));

        repository.findById(id)
                .map(lancamentoAtual -> {
                    lancamentoAtual.setCliente(cliente);
                    lancamentoAtual.setDataLancamento(lancamentoAtualizado.getDataLancamento());
                    lancamentoAtual.setCep(lancamentoAtualizado.getCep());
                    lancamentoAtual.setBairro(lancamentoAtualizado.getBairro());
                    lancamentoAtual.setLagradouro(lancamentoAtualizado.getLagradouro());
                    lancamentoAtual.setComplemento(lancamentoAtualizado.getComplemento());
                    lancamentoAtual.setNumero(lancamentoAtualizado.getNumero());
                    lancamentoAtual.setCelular(lancamentoAtualizado.getCelular());
                    lancamentoAtual.setTelefone(lancamentoAtualizado.getTelefone());
                    lancamentoAtual.setStatus(lancamentoAtualizado.getStatus());
                    lancamentoAtual.setTotal(lancamentoAtualizado.getTotal());
                    repository.save(lancamentoAtual);
                    return Void.TYPE;
                })
                .orElseThrow(() -> new ProdutoException("Pedido inexistente",""));
    }
}
