package io.github.erick.backendCllientes.service;

import io.github.erick.backendCllientes.exception.GenericException;
import io.github.erick.backendCllientes.exception.ProdutoException;
import io.github.erick.backendCllientes.model.entity.Adicional;
import io.github.erick.backendCllientes.model.entity.CategoriaProduto;
import io.github.erick.backendCllientes.model.entity.CategoriaProdutoAdicional;
import io.github.erick.backendCllientes.model.repository.AdicionalRepository;
import io.github.erick.backendCllientes.model.repository.CategoriaProdutoAdicionalRepository;
import io.github.erick.backendCllientes.model.repository.CategoriaProdutoRepository;
import io.github.erick.backendCllientes.rest.dto.CategoriaProdutoAdicionalDTO;
import io.github.erick.backendCllientes.rest.dto.CategoriaProdutoDTO;

import io.github.erick.backendCllientes.rest.dto.InfoCatAdicionalDTO;
import io.github.erick.backendCllientes.rest.dto.InfoCatProdDTO;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CategoriaProdutoService {

    private final CategoriaProdutoRepository repository;
    private final AdicionalRepository adicionalRepository;
    private final CategoriaProdutoAdicionalRepository categoriaProdutoAdicionalRepository;

    @Transactional
    public InfoCatProdDTO salvarCategoriaProduto(CategoriaProdutoDTO categoriaProdutoDTO){
        CategoriaProduto categoriaProduto = new CategoriaProduto();
        categoriaProduto.setNomeCategoriaProduto(categoriaProdutoDTO.getNomeCategoriaProduto());
        try {
            repository.save(categoriaProduto);
        }catch (Exception e){
            throw new GenericException("Dados Categoria invalidos", e.toString());
        }
        List<CategoriaProdutoAdicional> categoriaProdutoAdicional = converterAdicionais(categoriaProduto, categoriaProdutoDTO.getAdicionais());

        try {
            categoriaProdutoAdicionalRepository.saveAll(categoriaProdutoAdicional);
        }catch (Exception e){
            throw new GenericException("Dados Categoria invalidos", e.toString());
        }

        categoriaProduto.setCategoriaProdutoAdicional(categoriaProdutoAdicional);
        InfoCatProdDTO infoCatProdDTO = new InfoCatProdDTO();
        return infoCatProdDTO = converterInfoCatProdDTO(categoriaProduto);
    }

    private List<CategoriaProdutoAdicional> converterAdicionais(CategoriaProduto categoriaProduto, List<CategoriaProdutoAdicionalDTO> listaAdicionais){
        if (CollectionUtils.isEmpty(listaAdicionais)){
            Collections.emptyList();
        }


        return listaAdicionais.stream()
                .map(dto -> {

                    if (dto.getId() == null){
                        return null;
                    }

                    Integer idAdicional = dto.getId();
                    Adicional adicional = adicionalRepository
                            .findById(idAdicional)
                            .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Produto selecionado não existe"));

                    CategoriaProdutoAdicional categoriaProdutoAdicional = new CategoriaProdutoAdicional();
                    categoriaProdutoAdicional.setId(dto.getIdCatProAd());
                    categoriaProdutoAdicional.setAdicional(adicional);
                    categoriaProdutoAdicional.setCategoriaProduto(categoriaProduto);
                    return  categoriaProdutoAdicional;
                }).collect(Collectors.toList());
    }

    public InfoCatProdDTO converterInfoCatProdDTO(CategoriaProduto categoriaProduto){
        return InfoCatProdDTO.builder()
                .id(categoriaProduto.getId())
                .nomeCategoriaProduto(categoriaProduto.getNomeCategoriaProduto())
                .dataCadastro(categoriaProduto.getDataCadastro())
                .adicionais(converterInfoCatProdAdicionalDTO(categoriaProduto.getCategoriaProdutoAdicional()))
                .build();
    }

    private List<InfoCatAdicionalDTO> converterInfoCatProdAdicionalDTO(List<CategoriaProdutoAdicional> adicionais){
        if (CollectionUtils.isEmpty(adicionais)){
            Collections.emptyList();
        }
        return adicionais.stream().map(
                adicional ->
                        InfoCatAdicionalDTO.builder()
                                .idCatProAd(adicional.getId())
                                .id(adicional.getAdicional().getId())
                                .nome(adicional.getAdicional().getNome())
                                .build()
        ).collect(Collectors.toList());
    }

    public InfoCatProdDTO atualizarCategoriaProduto(Integer id, CategoriaProdutoDTO categoriaProdutoAtualizado) {

      return  repository.findById(id)
                .map(categoriaProduto1 -> {
                    categoriaProduto1.setNomeCategoriaProduto(categoriaProdutoAtualizado.getNomeCategoriaProduto());
                    List<CategoriaProdutoAdicional> categoriaProdutoAdicional = converterAdicionais(categoriaProduto1, categoriaProdutoAtualizado.getAdicionais());
                    try {
                        repository.save(categoriaProduto1);
                    } catch (Exception e) {
                        throw new GenericException("Dados Categoria invalidos", e.toString());
                    }

                    try {
                        categoriaProdutoAdicionalRepository.saveAll(categoriaProdutoAdicional);
                    } catch (Exception e) {
                        throw new GenericException("Dados Adicional invalidos", e.toString());
                    }

                    categoriaProduto1.setCategoriaProdutoAdicional(categoriaProdutoAdicional);
                    InfoCatProdDTO infoCatProdDTO = new InfoCatProdDTO();
                    return infoCatProdDTO = converterInfoCatProdDTO(categoriaProduto1);
                })
                .orElseThrow(() -> new ProdutoException("Categoria inexistente", ""));

    }

    public void deleteCategoriaProduto(Integer id){
        repository.findById(id)
                .map(categoriaProduto -> {
                    repository.delete(categoriaProduto);
                    return  Void.TYPE;
                })
                .orElseThrow(() -> new ProdutoException("Categoria inexistente",""));
    }


    public Page<InfoCatProdDTO> listaCategoriaProduto(Integer pagina, Integer tamanhoPagina) {
        PageRequest pageRequest = PageRequest.of(pagina, tamanhoPagina, Sort.by("dataCadastro").descending());

        Page<CategoriaProduto> categoriaProdutos = repository.findAll(pageRequest);
        Page<InfoCatProdDTO> infoCatProdDTOS = categoriaProdutos.map(this::converterInfoCatProdDTO);

        return infoCatProdDTOS;
    }



    public Page<CategoriaProduto> buscarCategoriaProduto(String filtro, Integer pagina, Integer tamanhoPagina){
        PageRequest pageRequest = PageRequest.of(pagina, tamanhoPagina, Sort.by("dataCadastro").descending());
        return repository.buscarCategoriaProduto(filtro, pageRequest);
    }

    public List<InfoCatProdDTO> CarregarCategoriaProduto(){

        List<CategoriaProduto> listaCategoria = new ArrayList<CategoriaProduto>();

        listaCategoria = repository.findAll();

        List<InfoCatProdDTO> listaInfo = new ArrayList<InfoCatProdDTO>();

       return listaInfo = converterLista(listaCategoria);

    }

    private List<InfoCatProdDTO> converterLista(List<CategoriaProduto> categoriaProdutos){

        return categoriaProdutos.stream()
                .map(dto -> {
                    if (dto.getId() == null){
                        return null;
                    }

                    Integer idProduto = dto.getId();
                    CategoriaProduto  categoriaProduto = repository
                            .findById(idProduto)
                            .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Produto selecionado não existe"));

                    InfoCatProdDTO infoCatProdDTO = new InfoCatProdDTO();
                    infoCatProdDTO.setNomeCategoriaProduto(categoriaProduto.getNomeCategoriaProduto());
                    infoCatProdDTO.setDataCadastro(categoriaProduto.getDataCadastro());
                    infoCatProdDTO.setId(categoriaProduto.getId());
                    infoCatProdDTO.setAdicionais(converterInfoCatProdAdicionalDTO(categoriaProduto.getCategoriaProdutoAdicional()));
                    return infoCatProdDTO;
                }).collect(Collectors.toList());
    }

    public InfoCatProdDTO getById(Integer id) {
        CategoriaProduto  categoriaProduto = repository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Produto selecionado não existe"));

        InfoCatProdDTO infoCatProdDTO = converterInfoCatProdDTO(categoriaProduto);


        return  infoCatProdDTO;
    }
}
