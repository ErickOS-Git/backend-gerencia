package io.github.erick.backendCllientes.model.repository;

import io.github.erick.backendCllientes.model.entity.TipoPagamento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TipoPagamentoRepository extends JpaRepository<TipoPagamento, Integer> {
    @Query("SELECT fp FROM TipoPagamento fp " +
            "WHERE (fp.descricao) LIKE %:filtro%")
    Page<TipoPagamento> buscaFormPg(@Param("filtro") String filtro, Pageable pageable);
}
