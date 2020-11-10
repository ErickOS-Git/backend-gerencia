package io.github.erick.backendCllientes.service;

import io.github.erick.backendCllientes.model.entity.TipoUsuario;
import io.github.erick.backendCllientes.model.repository.TipoUsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TipoUsuarioService {

    private  final TipoUsuarioRepository repository;

    public List<TipoUsuario> carregarTipoUsurio(){
        return repository.findAll();
    }
}
