package io.github.erick.backendCllientes.rest;


import io.github.erick.backendCllientes.model.entity.LancamentoProduto;
import io.github.erick.backendCllientes.rest.dto.InfoLanProdutoDTO;
import io.github.erick.backendCllientes.rest.dto.InfoLanProdutosDTO;
import io.github.erick.backendCllientes.rest.dto.LancProdArrayDto;
import io.github.erick.backendCllientes.rest.dto.LancamentoProdutosDTO;
import io.github.erick.backendCllientes.service.LancamentoProdutoService;
import io.github.erick.backendCllientes.service.LancamentoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/lancamentos-produtos")
@AllArgsConstructor
public class LancamentoProdutoController {

    private final LancamentoProdutoService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public List<InfoLanProdutoDTO> salvar(@RequestBody @Validated LancProdArrayDto prodArrayDto ){
        try {
            return service.salvar(prodArrayDto);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteLancamento(@PathVariable Integer id){
        try {
            service.deleteLancamento(id);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
