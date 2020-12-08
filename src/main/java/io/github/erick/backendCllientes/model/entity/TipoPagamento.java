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
   private Integer id;

    @Column(name = "tipo_pagamento")
    private String tipoPagamento;

    @Column
    private String descricao;

    @Column
    private Integer taxa;
}
