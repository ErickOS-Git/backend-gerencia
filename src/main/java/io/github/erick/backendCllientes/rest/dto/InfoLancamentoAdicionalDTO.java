package io.github.erick.backendCllientes.rest.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InfoLancamentoAdicionalDTO {

    private Integer lancamentoAdicionalId;
    private Integer lancamentoProdutoId;
    private Integer adicionalId;
    private String nome;
    private Boolean status;

}
