package io.github.erick.backendCllientes.model.repository;

import io.github.erick.backendCllientes.model.entity.LancamentoProduto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LancamentoProdutoRepository extends JpaRepository<LancamentoProduto, Integer> {
}
