package io.github.erick.backendCllientes.rest;

import io.github.erick.backendCllientes.exception.FornecedorException;
import io.github.erick.backendCllientes.model.entity.Fornecedor;
import io.github.erick.backendCllientes.model.repository.FornecedorRepository;
import io.github.erick.backendCllientes.service.FornecedorService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/fornecedor")
@RequiredArgsConstructor
public class FornecedorController {

    private final FornecedorService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Fornecedor createFornecedor(@RequestBody @Validated Fornecedor fornecedor){
        try {
            return service.save(fornecedor);
        }catch (FornecedorException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void upadteFornecedor(@PathVariable Integer id, @RequestBody @Validated Fornecedor fornecedorAtualizado){
        try{
             service.updateFornecedor(id, fornecedorAtualizado);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteFornecedor(@PathVariable Integer id){
        try {
            service.deleteFornecedor(id);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping
    public Page<Fornecedor> list
            (@RequestParam(value = "page", defaultValue = "0") Integer pagina,
             @RequestParam(value = "size",defaultValue = "10") Integer tamanhoPagina)
    {
        return service.listaTodos(pagina, tamanhoPagina);
    }

    @GetMapping("/buscarFornecedor")
    public Page<Fornecedor> buscarNomeCnpjCpf
            (@RequestParam("filtro") String filtro,
             @RequestParam(value = "page", defaultValue = "0") Integer pagina,
             @RequestParam(value = "size",defaultValue = "10") Integer tamanhoPagina)
    {
        if (filtro.isEmpty()) {
            return service.listaTodos(pagina, tamanhoPagina);
        }
        return service.buscarFornecedor(pagina, tamanhoPagina, filtro);
    }

}

