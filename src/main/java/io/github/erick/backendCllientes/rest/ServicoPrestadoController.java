package io.github.erick.backendCllientes.rest;

import io.github.erick.backendCllientes.model.entity.Cliente;
import io.github.erick.backendCllientes.model.entity.ServicoPrestado;
import io.github.erick.backendCllientes.model.repository.ClienteRepository;
import io.github.erick.backendCllientes.model.repository.ServicoPrestaadoRepository;
import io.github.erick.backendCllientes.rest.dto.ServicoPrestadoDTO;
import io.github.erick.backendCllientes.util.ReplaceString;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/api/servicos-prestados")
@AllArgsConstructor
public class ServicoPrestadoController {

    private final ClienteRepository clienteRepository;
    private final ServicoPrestaadoRepository repository;
    private final ReplaceString replaceString;



    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ServicoPrestado salvar(@RequestBody @Validated ServicoPrestadoDTO dto){
       Integer idCliente = dto.getIdCliente();

       String dataParaFormatar = dto.getData();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate data = LocalDate.parse(dataParaFormatar, formato);

        Cliente cliente =
               clienteRepository
               .findById(idCliente)
               .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cliente inexistente"));

       ServicoPrestado servicoPrestado = new ServicoPrestado();
       servicoPrestado.setDescricao(dto.getDescricao());
       servicoPrestado.setData(data);
       servicoPrestado.setCliente(cliente);
       servicoPrestado.setValor( replaceString.converter(dto.getPreco()));

       return repository.save(servicoPrestado);
    }

    @GetMapping
    public List<ServicoPrestado> pesquisar(
            @RequestParam(value = "nome", required = false,defaultValue = "") String nome,
            @RequestParam(value = "mes", required = false) Integer mes)
    {
        return repository.findByNomeClienteAndMes("%" + nome + "%", mes);


    }

}
