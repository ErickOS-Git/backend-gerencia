package io.github.erick.backendCllientes.model.repository;

import io.github.erick.backendCllientes.model.entity.Produto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

    @Query("SELECT p FROM Produto p " +
            "WHERE (p.nomeProduto) LIKE %:filtro%" )
    Page<Produto> buscarProduto(@Param("filtro") String filtro, Pageable pageable);
}
