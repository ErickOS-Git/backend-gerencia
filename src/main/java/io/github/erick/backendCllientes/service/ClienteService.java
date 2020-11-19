package io.github.erick.backendCllientes.service;
import io.github.erick.backendCllientes.exception.ClienteException;
import io.github.erick.backendCllientes.model.entity.Cliente;
import io.github.erick.backendCllientes.model.repository.ClienteRepository;
import io.github.erick.backendCllientes.util.ReplaceString;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    private ReplaceString replaceString;


    public Cliente salvar(Cliente cliente){
        cliente.setCpf(replaceString.repalceCPF(cliente.getCpf()));
        cliente.setRg(replaceString.replaceRg(cliente.getRg()));
        cliente.setTelefone(replaceString.replaceTelefone(cliente.getTelefone()));
        cliente.setCelular(replaceString.replaceCelular(cliente.getCelular()));
        boolean exists = repository.existsByCpf(cliente.getCpf());
        if(exists){
            throw new ClienteException("Cliente já cadastrado para o cpf: ", cliente.getCpf());
        }
        return  repository.save(cliente);
    }

    public Optional<Cliente> buscarCpf(String cpf){
        return repository.findByCpf(cpf);
    }

    public Optional<Cliente> buscarId(Integer  id){
        return  repository.findById(id);
    }

    public Page<Cliente> buscarCliente(Integer pagina, Integer tamanhoPagina, String filtro

    ){
        PageRequest pageRequest = PageRequest.of(pagina, tamanhoPagina);
        return  repository.buscarCliente(filtro,pageRequest);
    }

    public Page<Cliente> listarTodos(Integer pagina, Integer tamanhoPagina

    ){
        PageRequest pageRequest = PageRequest.of(pagina, tamanhoPagina);
        return  repository.findAll(pageRequest);
    }

    public void updateCliente(Integer id, Cliente clienteAtualizado){
        clienteAtualizado.setCpf(replaceString.repalceCPF(clienteAtualizado.getCpf()));

        if (repository.existsByCpf(clienteAtualizado.getCpf())){
            repository.findByCpf(clienteAtualizado.getCpf())
                    .map(cliente -> {
                        if (cliente.getId().equals(id)){
                            clienteAtualizado.setId(id);
                            clienteAtualizado.setRg(replaceString.replaceRg(clienteAtualizado.getRg()));
                            clienteAtualizado.setTelefone(replaceString.replaceTelefone(clienteAtualizado.getTelefone()));
                            clienteAtualizado.setCelular(replaceString.replaceCelular(clienteAtualizado.getCelular()));
                            repository.save(clienteAtualizado);
                            return Void.TYPE;
                        }else {
                            throw new ClienteException("Cliente já cadastrado para o cpf: ", cliente.getCpf());
                        }
                    }).orElseThrow(() ->  new ClienteException("Não foi possivel atualizar o Cliente.",""));
        }else if (!repository.existsByCpf(clienteAtualizado.getCpf())){
            repository.findById(id)
                    .map(cliente -> {
                        if (cliente.getId().equals(id)){
                            clienteAtualizado.setId(id);
                            clienteAtualizado.setTelefone(replaceString.replaceTelefone(clienteAtualizado.getTelefone()));
                            clienteAtualizado.setCelular(replaceString.replaceCelular(clienteAtualizado.getCelular()));
                            repository.save(clienteAtualizado);
                            return Void.TYPE;
                        }else {
                            throw new ClienteException("Cliente já cadastrado para o cpf: ", cliente.getCpf());
                        }
                    }).orElseThrow(() ->  new ClienteException("Não foi possivel atualizar o Cliente.",""));
        }
    }



    public void deleteCliente(Integer id){
        repository
                .findById(id)
                .map(cliente -> {
                    repository.delete(cliente);
                    return Void.TYPE;
                })
                .orElseThrow(() -> new ClienteException("Desculpe ocorreu um erro ao apagar o Cliente!", ""));
    }

}

