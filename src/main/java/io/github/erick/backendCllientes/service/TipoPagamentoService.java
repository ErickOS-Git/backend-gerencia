package io.github.erick.backendCllientes.service;

import io.github.erick.backendCllientes.exception.TipoPagamentoException;
import io.github.erick.backendCllientes.model.entity.TipoPagamento;
import io.github.erick.backendCllientes.model.repository.TipoPagamentoRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TipoPagamentoService {

    private final TipoPagamentoRepository repository;


    public TipoPagamento salvar(TipoPagamento tipoPagamento){
       return repository.save(tipoPagamento);
    }

    public void atualizar(Integer id, TipoPagamento tipoPagamento){

        repository.findById(id)
                .map(tipoPagamentoAtualizado ->{
                    tipoPagamentoAtualizado.setDescricao(tipoPagamento.getDescricao());
                    tipoPagamentoAtualizado.setTipoPagamento(tipoPagamento.getTipoPagamento());
                    tipoPagamentoAtualizado.setTaxa(tipoPagamento.getTaxa());
                    repository.save(tipoPagamentoAtualizado);
                    return Void.TYPE;
                } )
                .orElseThrow(() -> new TipoPagamentoException("Forma de pagamento Inexistente", ""));
    }
    public void delete(Integer id){
        repository.findById(id)
                .map(tipoPagamento -> {
                    repository.delete(tipoPagamento);
                    return Void.TYPE;
                })
                .orElseThrow(() -> new TipoPagamentoException("Forma de pagamento Inexistente", ""));
    }

    public Page<TipoPagamento> lista(Integer pagina, Integer tamanhoPagina){
        PageRequest pageRequest = PageRequest.of(pagina, tamanhoPagina);
        return repository.findAll(pageRequest);
    }

    public List<TipoPagamento> carregar(){
        return repository.findAll();
    }

    public Page<TipoPagamento> buscaTipoPagamento(String filtro, Integer pagina, Integer tamanhoPagina){
        PageRequest pageRequest = PageRequest.of(pagina, tamanhoPagina);
        return repository.buscaFormPg(filtro, pageRequest);
    }

}
