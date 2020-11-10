package io.github.erick.backendCllientes.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "categoria_produto")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoriaProduto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataCadastro;

    @Column(name = "nome_categoria_produto")
    private String NomeCategoriaProduto;

    @PrePersist
    public void prePersist(){
        setDataCadastro(LocalDate.now());
    }
}
