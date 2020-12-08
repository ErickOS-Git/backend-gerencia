package io.github.erick.backendCllientes.model.repository;

import io.github.erick.backendCllientes.model.entity.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    @Query("SELECT c FROM Cliente c " +
            "WHERE (c.nome) LIKE %:filtro% OR (c.cpf) LIKE %:filtro%" )
    Page<Cliente> buscarCliente(@Param("filtro") String filtro, Pageable pageable);

    Optional<Cliente> findByCpf(String cpf);

    boolean existsByCpf(String cpf);

    boolean existsById(Integer id);

}