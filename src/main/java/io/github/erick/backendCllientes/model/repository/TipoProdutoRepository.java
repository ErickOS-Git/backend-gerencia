package io.github.erick.backendCllientes.model.repository;


import io.github.erick.backendCllientes.model.entity.TipoProduto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TipoProdutoRepository extends JpaRepository<TipoProduto, Integer> {
    @Query("SELECT t FROM TipoProduto t " +
            "WHERE (t.nomeTipoProduto) LIKE %:filtro%" )
    Page<TipoProduto> buscartipoProduto(@Param("filtro") String filtro, Pageable pageable);
}
