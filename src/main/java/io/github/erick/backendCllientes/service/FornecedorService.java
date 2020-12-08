package io.github.erick.backendCllientes.service;

import io.github.erick.backendCllientes.exception.ClienteException;
import io.github.erick.backendCllientes.exception.FornecedorException;
import io.github.erick.backendCllientes.model.entity.Cliente;
import io.github.erick.backendCllientes.model.entity.Fornecedor;
import io.github.erick.backendCllientes.model.repository.FornecedorRepository;
import io.github.erick.backendCllientes.util.ReplaceString;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FornecedorService {

    private final FornecedorRepository repository;
    private ReplaceString replaceString;

    public Fornecedor save(Fornecedor fornecedor){

        fornecedor.setTelefone(replaceString.replaceTelefone(fornecedor.getTelefone()));
        fornecedor.setCelular(replaceString.replaceCelular(fornecedor.getCelular()));
        fornecedor.setCpf(replaceString.repalceCPF(fornecedor.getCpf()));
        fornecedor.setCnpj(replaceString.repalceCnpj(fornecedor.getCnpj()));

        if (fornecedor.getCnpj() != null){
            boolean existCnpj = repository.existsByCnpj(fornecedor.getCnpj());
            if (existCnpj){
                throw new FornecedorException("Já existe CNPJ cadastrado: ", fornecedor.getCnpj() );
            }
        }else if (fornecedor.getCpf() !=null){
            boolean existCpf = repository.existsByCpf(fornecedor.getCpf());
            if (existCpf){
                throw new FornecedorException("Já existe CPF cadastrado: ", fornecedor.getCpf());
            }
        }

        if (fornecedor.getCpf() == null && fornecedor.getCnpj() ==null){
            throw new FornecedorException("CPF ou CNPJ deve está preenchido!", "");
        }

        try {
            return repository.save(fornecedor);
        } catch (Exception e){
            throw new FornecedorException("Não foi possivel cadastrar o fornecedor.", e.toString());
        }

    }

    public void updateFornecedor(Integer id, Fornecedor fornecedorAtualizado  ){

        fornecedorAtualizado.setTelefone(replaceString.replaceTelefone(fornecedorAtualizado.getTelefone()));
        fornecedorAtualizado.setCelular(replaceString.replaceCelular(fornecedorAtualizado.getCelular()));
        fornecedorAtualizado.setCpf(replaceString.repalceCPF(fornecedorAtualizado.getCpf()));
        fornecedorAtualizado.setCnpj(replaceString.repalceCnpj(fornecedorAtualizado.getCnpj()));


        if (fornecedorAtualizado.getChecked() == false && fornecedorAtualizado.getCnpj() != null){
            if (repository.existsByCnpj(fornecedorAtualizado.getCnpj())){
                    repository.findByCnpj(fornecedorAtualizado.getCnpj())
                            .map(fornecedor -> {
                                if (fornecedor.getId().equals(id)){
                                    fornecedorAtualizado.setId(id);
                                    repository.save(fornecedorAtualizado);
                                    return Void.TYPE;
                                }else {
                                    throw new FornecedorException("CNPJ já cadastrado.","");
                                }
                            }).orElseThrow(() -> new FornecedorException("Desculpe não foi possivel atualizar o Fornecedor!",""));

            }else if (!repository.existsByCnpj(fornecedorAtualizado.getCnpj())){
                repository.findById(id)
                        .map(fornecedor -> {
                            if(fornecedor.getId().equals(id)){
                                fornecedorAtualizado.setId(id);
                                repository.save(fornecedorAtualizado);
                                return Void.TYPE;
                            }else {
                                throw new ClienteException("Fornecedor já cadastrado para o CNPJ: ", "");
                            }
                        }).orElseThrow(() -> new FornecedorException("Desculpe não foi possivel atualizar o Fornecedor!",""));
            }
        }

        else if (fornecedorAtualizado.getChecked() == true && fornecedorAtualizado.getCpf() != null){
            if (repository.existsByCpf(fornecedorAtualizado.getCpf())){
                repository.findByCpf(fornecedorAtualizado.getCpf())
                        .map(fornecedor -> {
                            if (fornecedor.getId().equals(id)){
                                fornecedorAtualizado.setId(id);
                                repository.save(fornecedorAtualizado);
                                return Void.TYPE;
                            }else {
                                throw new FornecedorException("CPF já cadastrado.","");
                            }
                        }).orElseThrow(() -> new FornecedorException("Desculpe não foi possivel atualizar o Fornecedor!",""));
            }else if (!repository.existsByCpf(fornecedorAtualizado.getCpf())){
                repository.findById(id)
                        .map(fornecedor -> {
                            if (fornecedor.getId().equals(id)){
                                fornecedorAtualizado.setId(id);
                                repository.save(fornecedorAtualizado);
                                return Void.TYPE;
                            }else {
                                throw new FornecedorException("CPF já cadastrado.","");
                            }
                        }).orElseThrow(() -> new FornecedorException("Desculpe não foi possivel atualizar o Fornecedor!",""));
            }
        }
    }


    public void  deleteFornecedor(Integer id){
        repository.findById(id)
                .map(fornecedor -> {
                    repository.deleteById(id);
                    return Void.TYPE;
                })
                .orElseThrow(()-> new FornecedorException("Desculpe ocorreu um erro ao apagar o fornecedor!",""));
    }

    public Page<Fornecedor> listaTodos(Integer pagina, Integer tamanhoPagina) {

        PageRequest pageRequest = PageRequest.of(pagina, tamanhoPagina,Sort.by("dataCadastro").descending());
        return  repository.findAll(pageRequest);
    }

    public Page<Fornecedor> buscarFornecedor(Integer pagina, Integer tamanhoPagina, String filtro

    ){
        PageRequest pageRequest = PageRequest.of(pagina, tamanhoPagina, Sort.by("dataCadastro").descending());
        return  repository.buscarFornecedor(filtro,pageRequest);
    }
}
