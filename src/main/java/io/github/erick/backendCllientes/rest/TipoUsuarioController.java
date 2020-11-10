package io.github.erick.backendCllientes.rest;

import io.github.erick.backendCllientes.exception.GenericException;
import io.github.erick.backendCllientes.model.entity.TipoUsuario;
import io.github.erick.backendCllientes.service.TipoUsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/tipo-usuario")
@AllArgsConstructor
public class TipoUsuarioController {

    private final TipoUsuarioService service;

    @GetMapping
    public List<TipoUsuario> carregarTipoUsuario(){
        try {
            return service.carregarTipoUsurio();
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }

    }
}
