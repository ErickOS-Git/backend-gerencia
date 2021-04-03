package io.github.erick.backendCllientes.rest;

import io.github.erick.backendCllientes.model.entity.Adicional;
import io.github.erick.backendCllientes.service.AdicionalService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


import java.util.List;

@RestController
@RequestMapping("/api/adicionais")
@AllArgsConstructor
public class AdicionalController {

    private final AdicionalService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Adicional salvar(@RequestBody @Validated Adicional adicional){
        try {
            return service.salvar(adicional);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizarAdicionais(@PathVariable Integer id, @RequestBody @Validated Adicional adicional){
        try {
             service.atualizarAdicionais(id, adicional);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void apagarAdicional(@PathVariable Integer id){
        try {
            service.apagar(id);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping
    public Page<Adicional> listaAdicionias(
            @RequestParam(value = "page", defaultValue = "0") Integer pagina,
            @RequestParam(value = "size",defaultValue = "10") Integer tamanhoPagina
    ){
        try {
            return service.listaAdicionias(pagina,tamanhoPagina);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("carregar-adicionais")
    public List<Adicional> carregarAdicionais(
            @RequestParam(value = "page", defaultValue = "0") Integer pagina,
            @RequestParam(value = "size",defaultValue = "10") Integer tamanhoPagina
    ){
        try {
            return service.carregarAdicionais();
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

  /*  @GetMapping("buscar-adicionais")
    public Page<Adicional> buscarAdicional(
            @RequestParam("filtro") String filtro,
            @RequestParam(value = "page", defaultValue = "0") Integer pagina,
            @RequestParam(value = "size",defaultValue = "10") Integer tamanhoPagina
    ){
        try {
            return service.buscarAdicional(pagina, tamanhoPagina, filtro);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    } */
}
