package io.github.erick.backendCllientes.service;

import io.github.erick.backendCllientes.exception.GenericException;
import io.github.erick.backendCllientes.model.entity.Adicional;
import io.github.erick.backendCllientes.model.repository.AdicionalRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AdicionalService {

    private final AdicionalRepository repository;


    public Adicional salvar(Adicional adicional){
        return repository.save(adicional);
    }

    public void atualizarAdicionais(Integer id, Adicional adicional){
        repository.findById(id).map(adicionalAtualizado -> {
            adicionalAtualizado.setNome(adicional.getNome());
            repository.save(adicionalAtualizado);
            return Void.TYPE;
        }).orElseThrow(() -> new GenericException("Item inexistente",""));
    }

    public Page<Adicional> listaAdicionias(Integer pagina, Integer tamanhoPagina){
        PageRequest pageRequest = PageRequest.of(pagina, tamanhoPagina, Sort.by("nome").descending());
        try {
            return repository.findAll(pageRequest);
        }catch (Exception e){
            throw new GenericException("Não foi possivel listar os adicionais","");
        }

    }

    public List<Adicional> carregarAdicionais(){
        try {
            return repository.findAll();
        }catch (Exception e) {
            throw new GenericException("Não foi possivel carregar os adicionais", "");
        }
    }

  public Page<Adicional> buscarAdicional(Integer pagina, Integer tamanhoPagina, String filtro){
        PageRequest pageRequest = PageRequest.of(pagina, tamanhoPagina, Sort.by("dataCadastro").descending());
        return repository.buscarAdicional(filtro, pageRequest);
    }

    public void apagar(Integer id){
        repository.findById(id).
                map(adicional -> {
                    repository.delete(adicional);
                    return Void.TYPE;
                }).orElseThrow(() -> new GenericException("Adicional inexistente",""));

    }

}
