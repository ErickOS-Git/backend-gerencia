package io.github.erick.backendCllientes.rest;

import io.github.erick.backendCllientes.model.entity.TipoProduto;
import io.github.erick.backendCllientes.service.TipoProdutoService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/tipo-produto")
@AllArgsConstructor
public class TipoProdutoController {

    private final TipoProdutoService service;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TipoProduto salvarTipoProduto(@RequestBody TipoProduto tipoProduto){
        try {
            return service.salvarTipoProduto(tipoProduto);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizarTipoProduto(@PathVariable Integer id, @RequestBody TipoProduto tipoProdutoAtualizado){
        try {
            service.atualizarTipoProduto(id, tipoProdutoAtualizado);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTipoProduto(@PathVariable  Integer id){
        try {
            service.deleteTipoProduto(id);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping
    public List<TipoProduto> carregarTipoProduto(){
        try {
            return  service.carregarTipoProduto();
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("/list")
    public Page<TipoProduto> listaTipoProduto
            (@RequestParam(value = "page", defaultValue = "0") Integer pagina,
             @RequestParam(value = "size",defaultValue = "10") Integer tamanhoPagina)
    {
        try {
            return service.listaTipoProduto(pagina, tamanhoPagina);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("buscar")
    public Page<TipoProduto> buscarTipoProduto
            (@RequestParam("filtro") String filtro,
             @RequestParam(value = "page", defaultValue = "0") Integer pagina,
             @RequestParam(value = "size",defaultValue = "10") Integer tamanhoPagina)
    {
        try {
            return service.buscarTipoProduto(filtro, pagina, tamanhoPagina);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }


}
