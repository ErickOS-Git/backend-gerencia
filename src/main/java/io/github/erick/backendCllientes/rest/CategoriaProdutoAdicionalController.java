package io.github.erick.backendCllientes.rest;

import io.github.erick.backendCllientes.exception.GenericException;
import io.github.erick.backendCllientes.model.repository.CategoriaProdutoAdicionalRepository;
import lombok.AllArgsConstructor;
import org.jboss.jandex.VoidType;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/categoria-adicional")
@AllArgsConstructor
public class CategoriaProdutoAdicionalController {

private final CategoriaProdutoAdicionalRepository repository;

    @DeleteMapping("{id}")
    public void apagar(@PathVariable Integer id){
        try {
            repository.findById(id)
                    .map(catAd -> {
                        repository.delete(catAd);
                        return Void.TYPE;
                    }).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Adicional vinculado inexistente"));
        }catch (Exception e){
            throw new GenericException("Dados Categoria invalidos", e.toString());
        }


    }
}
