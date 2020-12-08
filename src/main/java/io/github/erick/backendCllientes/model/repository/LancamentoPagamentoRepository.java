package io.github.erick.backendCllientes.model.repository;

import io.github.erick.backendCllientes.model.entity.LancamentoPagamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LancamentoPagamentoRepository extends JpaRepository<LancamentoPagamento, Integer> {
}
