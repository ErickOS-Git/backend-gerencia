package io.github.erick.backendCllientes.model.repository;

import io.github.erick.backendCllientes.model.entity.CategoriaProduto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CategoriaProdutoRepository extends JpaRepository<CategoriaProduto, Integer> {
    @Query("SELECT c FROM CategoriaProduto c " +
            "WHERE (c.NomeCategoriaProduto) LIKE %:filtro%" )
    Page<CategoriaProduto> buscarCategoriaProduto(@Param("filtro") String filtro, Pageable pageable);
}
