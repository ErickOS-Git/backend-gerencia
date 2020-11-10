package io.github.erick.backendCllientes.model.repository;

import io.github.erick.backendCllientes.model.entity.Empresa;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface EmpresaRepository extends JpaRepository<Empresa, Integer> {
}
