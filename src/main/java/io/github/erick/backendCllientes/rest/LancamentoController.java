package io.github.erick.backendCllientes.rest;

import io.github.erick.backendCllientes.rest.dto.InformacoesLancamentoDTO;
import io.github.erick.backendCllientes.rest.dto.LancamentoDTO;
import io.github.erick.backendCllientes.service.LancamentoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/lancamentos")
@AllArgsConstructor
public class LancamentoController {


    private final LancamentoService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public InformacoesLancamentoDTO salvar(@RequestBody LancamentoDTO lancamentoDTO ){
        try {
            return  service.salvar(lancamentoDTO);

        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    };



}
