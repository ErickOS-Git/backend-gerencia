package io.github.erick.backendCllientes.model.repository;

import io.github.erick.backendCllientes.model.entity.CategoriaProduto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CategoriaProdutoRepository extends JpaRepository<CategoriaProduto, Integer> {
    @Query("SELECT c FROM CategoriaProduto c " +
            "WHERE (c.nomeCategoriaProduto) LIKE %:filtro%" )
    Page<CategoriaProduto> buscarCategoriaProduto(@Param("filtro") String filtro, Pageable pageable);

    @Query("select c" +
            " from CategoriaProduto c left join c.categoriaProdutoAdicional")
    Page<CategoriaProduto> listaCategoriaProduto(Pageable pageable);
}
