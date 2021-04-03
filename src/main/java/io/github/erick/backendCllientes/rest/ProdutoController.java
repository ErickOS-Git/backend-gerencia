package io.github.erick.backendCllientes.rest;

import io.github.erick.backendCllientes.model.entity.CategoriaProduto;
import io.github.erick.backendCllientes.model.entity.Produto;
import io.github.erick.backendCllientes.model.entity.TipoProduto;
import io.github.erick.backendCllientes.rest.dto.CategoriaProdutoDTO;
import io.github.erick.backendCllientes.rest.dto.InfoCatProdDTO;
import io.github.erick.backendCllientes.rest.dto.InforProduto;
import io.github.erick.backendCllientes.rest.dto.ProdutoDTO;
import io.github.erick.backendCllientes.service.ProdutoService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/produtos")
@AllArgsConstructor
public class ProdutoController {

    private final ProdutoService service;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Produto salvarProduto(@RequestBody @Validated ProdutoDTO produtoDTO) {
        try {
            return service.salvarProduto(produtoDTO);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("/carregar-produtos")
    public List<InforProduto> carregarProdutos() {
        try {
            return service.carregarProdutos();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Não foi possivel carregar os produtos");
        }
    }

    @GetMapping
    public Page<Produto> list
            (@RequestParam(value = "page", defaultValue = "0") Integer pagina,
             @RequestParam(value = "size", defaultValue = "10") Integer tamanhoPagina) {
        try {
            return service.listaProduto(pagina, tamanhoPagina);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("/buscar-produto")
    public Page<Produto> list
            (@RequestParam("filtro") String filtro,
             @RequestParam(value = "page", defaultValue = "0") Integer pagina,
             @RequestParam(value = "size", defaultValue = "10") Integer tamanhoPagina) {
        try {
            return service.buscarProduto(pagina, tamanhoPagina, filtro);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizaProduto(@PathVariable Integer id, @RequestBody ProdutoDTO produtoDTOAtualizado) {
        try {
            service.atualizarProduto(id, produtoDTOAtualizado);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduto(@PathVariable Integer id) {
        try {
            service.deleteProduto(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
