package io.github.erick.backendCllientes.rest;

import io.github.erick.backendCllientes.exception.ClienteException;
import io.github.erick.backendCllientes.model.entity.Cliente;
import io.github.erick.backendCllientes.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@RestController
@RequestMapping("/api/clientes")

public class ClienteController {

    private final ClienteService service;

    @Autowired
    public ClienteController(ClienteService repository){
        this.service = repository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente createCliente(@RequestBody @Validated Cliente cliente) {
        try {
            return service.salvar(cliente);
        }catch (ClienteException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }

         }

    @GetMapping("/cpf/{cpf}")
    public Cliente findByCPF(@PathVariable String cpf){
        return service
                .buscarCpf(cpf)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Paciente não Encontrado"));
    }

    @GetMapping
    public Page<Cliente> list(
            @RequestParam(value = "page", defaultValue = "0") Integer pagina,
            @RequestParam(value = "size",defaultValue = "10") Integer tamanhoPagina
    ) {

            return service.listarTodos(pagina, tamanhoPagina);
    }

    @GetMapping("/buscarCliente")
    public Page<Cliente> buscarNomeOuCpf(
            @RequestParam("filtro") String filtro,
            @RequestParam(value = "page", defaultValue = "0") Integer pagina,
            @RequestParam(value = "size",defaultValue = "10") Integer tamanhoPagina

    ) {
        if (filtro.isEmpty()) {
            return service.listarTodos(pagina, tamanhoPagina);
        }
        return service.buscarCliente(pagina, tamanhoPagina, filtro);
    }

    @GetMapping("{id}")
    public Cliente findId(@PathVariable Integer id){

        return service
                .buscarId(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Paciente não Encontrado"));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCliente(@PathVariable Integer id){
         try {
             service.deleteCliente(id);
         }catch (ClienteException e){
             throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
         }
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCliente( @PathVariable Integer id, @RequestBody @Validated Cliente clienteAtualizado) {
       try {
           service.updateCliente(id, clienteAtualizado);
       }catch (ClienteException e){
           throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
       }
    }

}
