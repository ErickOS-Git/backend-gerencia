package io.github.erick.backendCllientes.model.repository;

import io.github.erick.backendCllientes.model.entity.CategoriaProdutoAdicional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CategoriaProdutoAdicionalRepository extends JpaRepository<CategoriaProdutoAdicional, Integer> {

}
