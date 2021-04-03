package io.github.erick.backendCllientes.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.Optional;

@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LancamentoAdicional  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @ManyToOne
    @JoinColumn
    private Adicional adicional;

 // @Column(name = "lancamento_id")

    @ManyToOne
    private Lancamento lancamento;

    @Column
    private  Boolean status;

    //@Column(name = "lancamento_produto_id")

    @ManyToOne
    private LancamentoProduto lancamentoProduto;


}
