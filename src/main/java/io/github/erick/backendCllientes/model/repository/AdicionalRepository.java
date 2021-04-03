package io.github.erick.backendCllientes.model.repository;

import io.github.erick.backendCllientes.model.entity.Adicional;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AdicionalRepository extends JpaRepository<Adicional, Integer> {

    @Query("SELECT a FROM Adicional a " +
            "WHERE (a.nome) LIKE %:filtro%" )
    Page<Adicional> buscarAdicional(@Param("filtro") String filtro, Pageable pageable);

 /*   Page<Adicional> buscarAdicional(@Param("filtro") String filtro, PageRequest pageRequest); */
}
