package io.github.erick.backendCllientes.rest;

import io.github.erick.backendCllientes.model.entity.CategoriaProduto;
import io.github.erick.backendCllientes.rest.dto.CategoriaProdutoDTO;
import io.github.erick.backendCllientes.rest.dto.InfoCatProdDTO;
import io.github.erick.backendCllientes.service.CategoriaProdutoService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/categoria-produtos")
@AllArgsConstructor
public class CategoriaProdutoController {

    private final CategoriaProdutoService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public InfoCatProdDTO salvarCategoriaProduto(@RequestBody CategoriaProdutoDTO categoriaProdutoDTO){
        try {
            return    service.salvarCategoriaProduto(categoriaProdutoDTO);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public InfoCatProdDTO atualizarCategoriaProduto(@PathVariable  Integer id, @RequestBody CategoriaProdutoDTO categoriaProdutoAtualizado){
        try {
            return service.atualizarCategoriaProduto(id, categoriaProdutoAtualizado);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCategoriaProduto(@PathVariable  Integer id){
        try {
            service.deleteCategoriaProduto(id);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("{id}")
    public InfoCatProdDTO getById(@PathVariable Integer id){
        try {
            return service.getById(id);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping
    public List<InfoCatProdDTO> carregarCategoriaProduto(){
        try {
            return service.CarregarCategoriaProduto();
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("/list")
    public Page<InfoCatProdDTO> listaCategoriaProduto
            (@RequestParam(value = "page", defaultValue = "0") Integer pagina,
             @RequestParam(value = "size",defaultValue = "10") Integer tamanhoPagina)
    {
        return service.listaCategoriaProduto(pagina, tamanhoPagina);
    }

    @GetMapping("/buscar")
    public Page<CategoriaProduto> buscarCategoriaProduto
            (@RequestParam("filtro") String filtro,
             @RequestParam(value = "page", defaultValue = "0") Integer pagina,
             @RequestParam(value = "size",defaultValue = "10") Integer tamanhoPagina)
    {

        return service.buscarCategoriaProduto(filtro, pagina, tamanhoPagina);
    }

}
