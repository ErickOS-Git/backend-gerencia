package io.github.erick.backendCllientes.model.repository;

import io.github.erick.backendCllientes.model.entity.TipoUsuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoUsuarioRepository extends JpaRepository<TipoUsuario, Integer>  {
}
