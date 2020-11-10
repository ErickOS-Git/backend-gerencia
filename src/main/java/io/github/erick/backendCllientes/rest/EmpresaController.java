package io.github.erick.backendCllientes.rest;

import io.github.erick.backendCllientes.exception.ClienteException;
import io.github.erick.backendCllientes.model.entity.Empresa;
import io.github.erick.backendCllientes.service.EmpresaService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.Part;
import java.util.Optional;

@RestController
@RequestMapping("/api/empresa")
@AllArgsConstructor
public class EmpresaController {

    private final EmpresaService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Empresa salvar(@RequestBody Empresa empresa){
        try {
            return service.salvar(empresa);
        }catch (ClienteException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar(@PathVariable Integer id, @RequestBody Empresa empresaAtualizada){
        try {
            service.atualizar(id, empresaAtualizada);
        }catch (ClienteException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
    @PutMapping("{id}/upload")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public byte[] logoTipo(@PathVariable Integer id, @RequestParam("foto")Part foto){
        try {
          return  service.logotipo(id, foto);
        }catch (ClienteException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping
    public Optional<Empresa> carregar(){
        try {
            return service.carregar();
        }catch (ClienteException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PatchMapping("{id}/delete")
    public void apargarLogo(@PathVariable Integer id, @RequestBody Empresa empresa){
        try {
            System.out.println("Salvo");
              service.apagarLogo(id, empresa);
        }catch (ClienteException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }


}
