package io.github.erick.backendCllientes.rest;


import io.github.erick.backendCllientes.exception.ProdutoException;
import io.github.erick.backendCllientes.model.entity.TipoPagamento;
import io.github.erick.backendCllientes.service.TipoPagamentoService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.awt.*;
import java.util.List;

@RestController
@RequestMapping("/api/forma-pagamento")
@AllArgsConstructor
public class TipoPagamentoController {

    private final TipoPagamentoService service;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TipoPagamento create(@RequestBody TipoPagamento tipoPagamento){
        try {
            return service.salvar(tipoPagamento);
        }catch (ProdutoException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Integer id, @RequestBody TipoPagamento tipoPagamento){
        try {
            service.atualizar(id,tipoPagamento);
        }catch (ProdutoException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id){
        try {
            service.delete(id);
        }catch (ProdutoException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping
    public List<TipoPagamento> carregar(){
        try {
            return service.carregar();
        }catch (ProdutoException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("/page")
    public Page<TipoPagamento> listar(
            @RequestParam(value = "page", defaultValue = "0") Integer pagina,
            @RequestParam(value = "size",defaultValue = "10") Integer tamanhoPagina
    ){
        try {
            return service.lista(pagina,tamanhoPagina);
        }catch (ProdutoException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("/buscar")
    public Page<TipoPagamento> buscarTipoPagamento
            (@RequestParam("filtro") String filtro,
             @RequestParam(value = "page", defaultValue = "0") Integer pagina,
             @RequestParam(value = "size",defaultValue = "10") Integer tamanhoPagina)
    {
        try {
            return service.buscaTipoPagamento(filtro,pagina,tamanhoPagina);
        }catch (ProdutoException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
