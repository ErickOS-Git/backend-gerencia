package io.github.erick.backendCllientes.model.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
@Data
@Entity
@Getter@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LancamentoPagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn ( name = "id_lancamento")
    private Lancamento lancamento;

    @ManyToOne
    @JoinColumn ( name = "id_forma_pagamento")
    private TipoPagamento tipoPagamento;

    @Column
    private Integer desconto;

    @Column
    private BigDecimal valorpago;

    @Column
    private Integer taxa;


}
