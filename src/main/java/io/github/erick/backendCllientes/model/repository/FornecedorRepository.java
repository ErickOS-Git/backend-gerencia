package io.github.erick.backendCllientes.model.repository;



import io.github.erick.backendCllientes.model.entity.Fornecedor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface FornecedorRepository extends JpaRepository<Fornecedor, Integer> {

    @Query("SELECT f FROM Fornecedor f " +
            "WHERE (f.razaoSocial) LIKE %:filtro% OR (f.cpf) LIKE %:filtro% OR (f.cnpj) LIKE %:filtro%" )
    Page<Fornecedor> buscarFornecedor(@Param("filtro") String filtro, Pageable pageable);

    Optional<Fornecedor> findByCnpj(String cnpj);
    Optional<Fornecedor> findByCpf(String cpf);

    boolean existsByCpf(String cpf);
    boolean existsByCnpj(String cnpj);

}
