package io.github.erick.backendCllientes.rest.dto;

import io.github.erick.backendCllientes.model.entity.Lancamento;
import lombok.*;

import java.math.BigDecimal;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LancamentoProdutosDTO {

    private Integer produto;
    private BigDecimal valor_venda;
    private BigDecimal desconto;
    private Integer quantidade;


}
