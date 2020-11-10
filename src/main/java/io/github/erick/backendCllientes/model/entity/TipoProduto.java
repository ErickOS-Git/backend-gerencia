package io.github.erick.backendCllientes.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "tipo_produto")
@Getter@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TipoProduto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "data_cadastro")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataCadastro;

    @Column(name = "nome_tipo_produto")
    private String nomeTipoProduto;

    @PrePersist
    public void prePersist(){
        setDataCadastro(LocalDate.now());
    }
}

