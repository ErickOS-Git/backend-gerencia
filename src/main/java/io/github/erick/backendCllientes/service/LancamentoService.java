package io.github.erick.backendCllientes.service;

import io.github.erick.backendCllientes.exception.ClienteException;
import io.github.erick.backendCllientes.exception.GenericException;
import io.github.erick.backendCllientes.model.entity.*;
import io.github.erick.backendCllientes.model.repository.*;
import io.github.erick.backendCllientes.rest.dto.InfoLanProdutosDTO;
import io.github.erick.backendCllientes.rest.dto.InformacoesLancamentoDTO;
import io.github.erick.backendCllientes.rest.dto.LancamentoDTO;
import io.github.erick.backendCllientes.rest.dto.LancamentoProdutosDTO;
import lombok.AllArgsConstructor;
import org.jboss.jandex.VoidType;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class LancamentoService {

    private final LancamentoRepository repository;
    private final LancamentoProdutoRepository lancamentoProdutoRepository;
    private final ClienteRepository clienteRepository;
    private final ProdutoRepository produtoRepository;

    //Responsável por salvar os Lancamentos e a forma de pagamento e os produtos!
    @Transactional
    public InformacoesLancamentoDTO salvar(LancamentoDTO lancamentoDTO) {
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
        lancamento.setTotal(lancamentoDTO.getTotal());
try {
    repository.save(lancamento);
}catch (Exception e){
    throw new GenericException("Dados do pedido inválidos", e.toString());
}

        List<LancamentoProduto> lancamentoProduto = converterProdutos(lancamento, lancamentoDTO.getProdutos());

try {
    lancamentoProdutoRepository.saveAll(lancamentoProduto);
}catch (Exception e){
    throw new GenericException("Dados do pedido inválidos", e.toString());
}

        lancamento.setLancamentoProdutos(lancamentoProduto);
        InformacoesLancamentoDTO informacoesLancamentoDTO = new InformacoesLancamentoDTO();

        return informacoesLancamentoDTO = converter(lancamento);
    }

    //Converter os produtos que vem do lancamentoDTO em uma lista de produtos
    private List<LancamentoProduto> converterProdutos(Lancamento lancamento, List<LancamentoProdutosDTO> listProdutos) {

            if(listProdutos.isEmpty()) {
                throw new GenericException("Informe pelo menos um produto","");
            }



        return listProdutos.stream()
                .map(dto -> {
                    if (dto.getProduto() == null){
                        throw new GenericException("Informe pelo menos um produto","");
                    }
                    Integer idProduto = dto.getProduto();

                    Produto produto = produtoRepository
                            .findById(idProduto)
                            .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Produto selecionado não existe"));
                    LancamentoProduto lancamentoProduto = new LancamentoProduto();
                    lancamentoProduto.setLancamento(lancamento);
                    lancamentoProduto.setDesconto(dto.getDesconto());
                    lancamentoProduto.setQuantidade(dto.getQuantidade());
                    lancamentoProduto.setValorVenda(dto.getValor_venda());
                    lancamentoProduto.setProduto(produto);
                    return lancamentoProduto;
                }).collect(Collectors.toList());
    }


    //Transformar os dados salvos para exibir ao usuario
    private InformacoesLancamentoDTO converter(Lancamento lancamento){
       return InformacoesLancamentoDTO.builder()
                .codigoLancamento(lancamento.getId())
                .nomeCliente(lancamento.getCliente().getNome())
                .dataLancamento(lancamento.getDataLancamento())
                .dataCadastro(lancamento.getDataCadastro())
                .total(lancamento.getTotal())
                .produtos(converter(lancamento.getLancamentoProdutos()))
                .build();
    }

    // transforma o LancamentoProdutos em InfoLanProdutosDTO para exibir em InformacoesLancamentoDTO
    private List<InfoLanProdutosDTO> converter(List<LancamentoProduto> produtos){
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

}
