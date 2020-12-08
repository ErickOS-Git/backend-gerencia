package io.github.erick.backendCllientes.model.repository;

import io.github.erick.backendCllientes.model.entity.Lancamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface LancamentoRepository extends JpaRepository<Lancamento, Integer> {
}
