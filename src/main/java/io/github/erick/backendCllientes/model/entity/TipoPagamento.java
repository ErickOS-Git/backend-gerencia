package io.github.erick.backendCllientes.model.entity;


import lombok.*;

import javax.persistence.*;

@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TipoPagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(name = "tipo_pagamento")
    String tipoPagamento;

    @Column
    String descricao;

    @Column
    Integer taxa;

    @Column
    String banco;
}
