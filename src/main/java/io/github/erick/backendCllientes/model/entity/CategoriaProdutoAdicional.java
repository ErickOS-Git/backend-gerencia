package io.github.erick.backendCllientes.model.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
public class CategoriaProdutoAdicional {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JsonBackReference
    @JsonSerialize
    private CategoriaProduto categoriaProduto;

    @ManyToOne
    private Adicional adicional;

    public CategoriaProdutoAdicional() {
    }
}
