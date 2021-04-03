package io.github.erick.backendCllientes.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
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
    private String nomeCategoriaProduto;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "categoriaProduto",cascade = CascadeType.REMOVE)
    @JsonManagedReference
    private List<CategoriaProdutoAdicional> categoriaProdutoAdicional;

    @PrePersist
    public void prePersist(){
        setDataCadastro(LocalDate.now());
    }


}
