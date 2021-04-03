package io.github.erick.backendCllientes.rest.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LancamentoProdutosDTO {

    private Integer id;
    private Integer produto;
    private BigDecimal valorVenda;
    private BigDecimal desconto;
    private BigDecimal totalProduto;
    private Integer quantidade;
    private List<LancamentoAdicionalDTO> adicionais;

}
