package io.github.erick.backendCllientes.rest;

import io.github.erick.backendCllientes.model.entity.CategoriaProduto;
import io.github.erick.backendCllientes.model.entity.Produto;
import io.github.erick.backendCllientes.model.entity.TipoProduto;
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

    /*----------PRODUTO-----------------------*/

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Produto salvarProduto(@RequestBody @Validated ProdutoDTO produtoDTO){
        try {
            return service.salvarProduto(produtoDTO);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("/carregar-produtos")
    public List<Produto> carregarProdutos(){
        try {
            return service.carregarProdutos();
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Não foi possivel carregar os produtos");
        }
    }

    @GetMapping
    public Page<Produto> list
            (@RequestParam(value = "page", defaultValue = "0") Integer pagina,
             @RequestParam(value = "size",defaultValue = "10") Integer tamanhoPagina)
    {
        try {
            return service.listaProduto(pagina, tamanhoPagina);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("/buscar-produto")
    public Page<Produto> list
            (@RequestParam("filtro") String filtro,
             @RequestParam(value = "page", defaultValue = "0") Integer pagina,
             @RequestParam(value = "size",defaultValue = "10") Integer tamanhoPagina)
    {
        try {
            return service.buscarProduto(pagina, tamanhoPagina, filtro);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizaProduto(@PathVariable Integer id, @RequestBody ProdutoDTO produtoDTOAtualizado){
        try {
            service.atualizarProduto(id, produtoDTOAtualizado);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduto(@PathVariable Integer id){
        try {
            service.deleteProduto(id);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    /*----------CATEGORIA PRODUTO-----------------------*/

    @PostMapping("/categoria-produto")
    @ResponseStatus(HttpStatus.CREATED)
    public CategoriaProduto salvarCategoriaProduto(@RequestBody CategoriaProduto categoriaProduto){
        try {
            return    service.salvarCategoriaProduto(categoriaProduto);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PutMapping("/categoria-produto/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizarCategoriaProduto(@PathVariable  Integer id, @RequestBody CategoriaProduto categoriaProdutoAtualizado){
        try {
            service.atualizarCategoriaProduto(id, categoriaProdutoAtualizado);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @DeleteMapping("/categoria-produto/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCategoriaProduto(@PathVariable  Integer id){
        try {
            service.deleteCategoriaProduto(id);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("/categoria-produto")
    public List<CategoriaProduto> carregarCategoriaProduto(){
        try {
            return service.CarregarCategoriaProduto();
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
    @GetMapping("/categoria-produto/list")
    public Page<CategoriaProduto> listaCategoriaProduto
            (@RequestParam(value = "page", defaultValue = "0") Integer pagina,
             @RequestParam(value = "size",defaultValue = "10") Integer tamanhoPagina)
    {
        return service.listaCategoriaProduto(pagina, tamanhoPagina);
    }

    @GetMapping("/categoria-produto/buscar")
    public Page<CategoriaProduto> buscarCategoriaProduto
            (@RequestParam("filtro") String filtro,
             @RequestParam(value = "page", defaultValue = "0") Integer pagina,
             @RequestParam(value = "size",defaultValue = "10") Integer tamanhoPagina)
    {

        return service.buscarCategoriaProduto(filtro, pagina, tamanhoPagina);
    }





    /*----------TIPO PRODUTO-----------------------*/

    @PostMapping("/tipo-produto")
    @ResponseStatus(HttpStatus.CREATED)
    public TipoProduto salvarTipoProduto(@RequestBody TipoProduto tipoProduto){
        try {
            return service.salvarTipoProduto(tipoProduto);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
    @PutMapping("/tipo-produto/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizarTipoProduto(@PathVariable  Integer id, @RequestBody TipoProduto tipoProdutoAtualizado){
        try {
            service.atualizarTipoProduto(id, tipoProdutoAtualizado);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @DeleteMapping("/tipo-produto/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTipoProduto(@PathVariable  Integer id){
        try {
            service.deleteTipoProduto(id);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("/tipo-produto")
    public List<TipoProduto> listaTipoProduto(){
        try {
            return  service.carregarTipoProduto();
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("/tipo-produto/list")
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

    @GetMapping("/tipo-produto/buscar")
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
