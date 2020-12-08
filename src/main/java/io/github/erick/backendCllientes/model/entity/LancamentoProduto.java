package io.github.erick.backendCllientes.model.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
@Getter@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LancamentoProduto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn
    private Lancamento lancamento;

    @ManyToOne
    @JoinColumn
    private Produto produto;

    @Column
    private BigDecimal valorVenda;

    @Column
    private BigDecimal desconto;

    @Column
    private Integer quantidade;

}
